/*  Student information for assignment:
 *
 *  On my honor, Joey Muffoletto, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 2 :(
 *  UTEID: jrm7925
 *  email address: jrmuff@utexas.edu    
 *  Grader name: Andrew
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimpleHuffProcessor implements IHuffProcessor
{

    private final int ONE_BIT = 1;

    private IHuffViewer myViewer;

    private Map<Integer, String> chunkMap; //maps a given ASCII key to its tree-traversal-code 
    private Map<String, Integer> mapOfBits; //maps a given chunk (stored as string of length 8), to its frequency
    private HuffmanTree hf;

    private ArrayList<Integer> preOrderTraversal;

    private int preprocessCompressReturnVal;

    private boolean isSCF;

    /**
     * Preprocess data so that compression is possible ---
     * count characters/create tree/store state so that
     * a subsequent call to compress will work. The InputStream
     * is <em>not</em> a BitInputStream, so wrap it int one as needed.
     * @param in is the stream which could be subsequently compressed
     * @param headerFormat a constant from IHuffProcessor that determines what kind of
     * header to use, standard count format, standard tree format, or
     * possibly some format added in the future.
     * @return number of bits saved by compression or some other measure
     * Note, to determine the number of
     * bits saved, the number of bits written includes
     * ALL bits that will be written including the
     * magic number, the header format number, the header to
     * reproduce the tree, AND the actual data.
     * @throws IOException if an error occurs while reading from the input file.
     */
    public int preprocessCompress(InputStream in, int headerFormat) throws IOException
    {

        //reset ppCRV b/c testers use the same SHP object
        preprocessCompressReturnVal = 0;
        //generate a map of the byte frequencies
        mapOfBits = generateMap(in);
        //pass the byte frequencies to create a huffman tree
        hf = new HuffmanTree(mapOfBits);
        //get the encoding map from huff tree
        chunkMap = hf.getChunkMap();

        //is Standard Count Format? if not, its STF
        isSCF = headerFormat == STORE_COUNTS;

        //getting the correct return value for ppCRV;
        if (!isSCF)
        {
            preOrderTraversal = hf.getPreorderTraversal();
            preprocessCompressReturnVal -= hf.getBitSize();
            //32 bit number which represents tree size 
            preprocessCompressReturnVal -= BITS_PER_INT;
        } else
        {
            preprocessCompressReturnVal -= (ALPH_SIZE * BITS_PER_INT);
        }
        //subtract encoded bit size (includes pEOF)
        for (Integer key : chunkMap.keySet())
        {
            //multiply the length of the encoded chunk by the frequency of its ASCII val in the original file
            String keyValInBitForm = "" + key;
            preprocessCompressReturnVal -= chunkMap.get(key).length() * mapOfBits.get(keyValInBitForm);
        }
        //subtract MagicNum bit size
        preprocessCompressReturnVal -= BITS_PER_INT;
        //subtract STF or SCF value
        preprocessCompressReturnVal -= BITS_PER_INT;
        //preprocessCompressReturnVal

        return preprocessCompressReturnVal;
    }

    /*
     * method that returns a map of the byte frequencies
     * Map<String, Integer>: key = the byte, value = frequency of the byte
     */
    private Map<String, Integer> generateMap(InputStream in) throws IOException
    {

        int inbits = 0;
        Map<String, Integer> mapOfBits = new HashMap<>();
        BitInputStream bits = new BitInputStream(in);
        try
        {
            //get all bytes in file
            while ((inbits = bits.readBits(BITS_PER_WORD)) != -1)
            {
                String currByte = "" + inbits;
                if (mapOfBits.containsKey(currByte))
                {
                    int currVal = mapOfBits.get(currByte);
                    mapOfBits.put(currByte, currVal + 1);
                } else
                {
                    mapOfBits.put(currByte, 1);
                }
                //add each byte to the return val
                preprocessCompressReturnVal += BITS_PER_WORD;
            }
        } catch (IOException e)
        {
            myViewer.showError("IOException thrown when preprocessing");
        }
        //add PEFO
        mapOfBits.put("" + PSEUDO_EOF, 1);
        bits.close();
        return mapOfBits;
    }

    /*
     * Compresses input to output, where the same InputStream has
     * previously been pre-processed via <code>preprocessCompress</code>
     * storing state used by this call.
     * <br> pre: <code>preprocessCompress</code> must be called before this method
     * @param in is the stream being compressed (NOT a BitInputStream)
     * @param out is bound to a file/stream to which bits are written
     * for the compressed file (not a BitOutputStream)
     * @param force if this is true create the output file even if it is larger than the input file.
     * If this is false do not create the output file if it is larger than the input file.
     * @return the number of bits written.
     * @throws IOException if an error occurs while reading from the input file or
     * writing to the output file.
     */
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException
    {
        int bitCount = 0; // return value
        //checks if force compression && ppCRV returns a negative value (ie: compressed file will be bigger)
        if (!force && preprocessCompressReturnVal < 0)
        {
            myViewer.showError("Compressed file has " + Math.abs(preprocessCompressReturnVal)
                    + " more bits than uncompressed file. Select \"force compression\" to compress.");
        } else
        {
            try
            {
                BitOutputStream output = new BitOutputStream(out);
                bitCount += printHeaderConstants(output);
                bitCount += printAsBits(output);
                bitCount += printCompressedText(in, output);
                output.close();
            } catch (IOException e)
            {
                myViewer.showError("unexpected error while writing compressed text");
            }
        }
        return bitCount;
    }

    /*
     * method that writes the header constants (magic number and STF or SCF)
     * returns the amount of bits written (always will be 64)
     */
    private int printHeaderConstants(BitOutputStream output)
    {

        //MAGIC_NUMBER
        output.writeBits(BITS_PER_INT, MAGIC_NUMBER);
        //SCF or STF
        if (isSCF)
            output.writeBits(BITS_PER_INT, STORE_COUNTS);
        else
            output.writeBits(BITS_PER_INT, STORE_TREE);

        return BITS_PER_INT * 2;

    }

    /*
     * delegator/manager method that either calls a SCF write method or STF write method
     * returns how many bits were written
     */
    private int printAsBits(BitOutputStream output)
    {
        return isSCF ? printSCF(output) : printSTF(output);
    }

    /*
     * writes the SCF of the given frequency map for the file
     * returns the number of bits written
     */
    private int printSCF(BitOutputStream output)
    {
        int totalBitCount = 0;
        //runs for all 256 possible byte values
        for (int i = 0; i < ALPH_SIZE; i++)
        {
            if (mapOfBits.containsKey("" + i)) //frequency other than 0? write the actual value
            {
                output.writeBits(BITS_PER_INT, mapOfBits.get("" + i));
            } else
            {
                output.writeBits(BITS_PER_INT, 0);
            }
            totalBitCount += BITS_PER_INT;
        }
        return totalBitCount;
    }

    /*
     * writes the STF by using the preOrderTraversal of the HF tree
     * returns the number of bits written
     */
    private int printSTF(BitOutputStream output)
    {
        int totalBitCount = 0;

        //print 32 bit size of tree
        output.writeBits(BITS_PER_INT, hf.getBitSize());
        totalBitCount += BITS_PER_INT;
        //obtained a preOrderTraversal in precompression method. (Huff tree handles this internally)
        //pOT stores the list of all nodes in the tree. internal nodes all have vals of 0 and leafs have a pre-flag of 1 
        for (Integer i : preOrderTraversal)
        {
            //if you are a LEAF NODE (not including the "1" that comes before it), then write yourself 
            if (i > 1)
            {
                output.writeBits(BITS_PER_WORD + 1, i);
                totalBitCount += BITS_PER_WORD + 1;
            } else
            {
                output.writeBits(ONE_BIT, i);
                totalBitCount += ONE_BIT;
            }
        }
        return totalBitCount;
    }

    /*
     * method that writes the actual compressed version of the file
     * returns how many bits were written
     */
    private int printCompressedText(InputStream in, BitOutputStream output) throws IOException
    {
        int totalBitCount = 0;
        int inbits = 0;
        BitInputStream bits = new BitInputStream(in);

        while ((inbits = bits.readBits(IHuffConstants.BITS_PER_WORD)) != -1)
        {
            //gets the encodedChunk for this byte from huffman tree
            String encodedChunk = chunkMap.get(inbits);
            for (int i = 0; i < encodedChunk.length(); i++)
            {
                //writes it one bit a time
                int chunk = encodedChunk.charAt(i);
                output.writeBits(ONE_BIT, chunk);
                totalBitCount += ONE_BIT;
            }
        }
        //write PEOF

        String pEOF = chunkMap.get(PSEUDO_EOF);
        for (int i = 0; i < pEOF.length(); i++)
        {
            output.writeBits(ONE_BIT, pEOF.charAt(i));
            totalBitCount += ONE_BIT;
        }
        //close bitinputstream
        bits.close();
        return totalBitCount;
    }

    /**
     * Uncompress a previously compressed stream in, writing the
     * uncompressed bits/data to out.
     * @param in is the previously compressed data (not a BitInputStream)
     * @param out is the uncompressed file/stream
     * @return the number of bits written to the uncompressed file/stream
     * @throws IOException if an error occurs while reading from the input file or
     * writing to the output file.
     */
    public int uncompress(InputStream in, OutputStream out) throws IOException
    {
        BitInputStream input = new BitInputStream(in);
        int resultBits = 0;
        //check for magic
        if (input.readBits(BITS_PER_INT) != MAGIC_NUMBER)
            myViewer.showError("Error reading compressed file. File did not start with the huff magic number.");
        else
        {
            try
            {
                isSCF = input.readBits(BITS_PER_INT) == STORE_COUNTS; //STF or SCF?
                if (isSCF)
                    hf = rebuildTreeSCF(input); //go to SCF method
                else
                    hf = rebuildTreeSTF(input); //go to STF method
                //walks the newly build HF tree
                resultBits = walkTheTree(input, out);
            } catch (IOException e)
            {
                myViewer.showError("Encountered IOException while uncompressing file");
            }
        }

        return resultBits;
    }

    /*
     * method to rebuild the huffman tree from the SCF
     * returns the associated rebuilt HuffmanTree
     */
    private HuffmanTree rebuildTreeSCF(BitInputStream input) throws IOException
    {
        mapOfBits = new HashMap<String, Integer>();
        for (int i = 0; i < ALPH_SIZE; i++)
        {
            int currFreq = input.readBits(BITS_PER_INT);
            if (currFreq > 0)
            {
                mapOfBits.put("" + i, currFreq);
            }
        }
        //adding pEOF
        mapOfBits.put("" + PSEUDO_EOF, 1);

        return new HuffmanTree(mapOfBits);
    }

    /*
     * method to rebuild the huffman tree from the STF
     * creates an arraylist of all nodes
     * uses a recursive helper to build the tree from the arraylist
     * returns the associated rebuilt tree
     */
    private HuffmanTree rebuildTreeSTF(BitInputStream input) throws IOException
    {
        //rebuilding HuffmanTree 
        ArrayList<TreeNode> arr = new ArrayList<>();

        int bitMax = input.readBits(BITS_PER_INT); //how many bits in tree? (dont exceed, use for while loop)
        int currBits = 0; //bits read so far
        while (currBits < bitMax)
        {
            int curr = input.readBits(ONE_BIT);
            if (curr == -1)
            {
                throw new IOException("yea something happened bucko. rebuilding tree encountered IOException");
            }
            if (curr == 0)
            { //val = -1, so that way I know its an internal
                TreeNode node = new TreeNode(-1, 0);
                arr.add(node);

            } else if (curr == 1) //if 1, expect a node, read the next 9 bits
            {
                curr = input.readBits(BITS_PER_WORD + 1);
                TreeNode node = new TreeNode(curr, -1);
                arr.add(node);

                currBits += BITS_PER_WORD + 1;
            }
            currBits += ONE_BIT;
        }
        TreeNode root = new TreeNode(-1, -1);
        treeIndex = 0; //have to reset global index because automated tester reuses SHP object
        root = buildTree(root, arr, 0);
        return new HuffmanTree(root); //utilizes the hf constructor that allows you to pass a root node
    }

    int treeIndex = 0; //use a second global index so that all recursive calls know the correct index
    /*
     * recursively builds the tree given a list of nodes
     * uses treeindex so all recursive calls have the same index
     * returns the root node and all of its children :)
     */

    private TreeNode buildTree(TreeNode n, ArrayList<TreeNode> arr, int index)
    {
        //base case: all nodes exhausted, start popping stack *pop* *pop*
        if (treeIndex == arr.size())
            return null;
        else
        {
            n = arr.get(treeIndex);
            treeIndex++;
            if (n.getValue() == -1)
            {
                //kind of like a preorder traversal in that it favors the left side first
                n.setLeft(buildTree(n.getLeft(), arr, index));
                n.setRight(buildTree(n.getRight(), arr, index));
            }
        }
        return n;
    }

    /*
     * method that walks the rebuilt huff tree and writes the uncompressed file
     * returns how many bits it wrote
     */
    private int walkTheTree(BitInputStream input, OutputStream output) throws IOException
    {
        boolean done = false;
        int writtenBitCount = 0;

        TreeNode root = hf.getRoot();
        TreeNode node = root;
        //thanks for the algorithm Mr. Scott
        while (!done)
        {
            int bit = input.readBits(ONE_BIT);
            //shouldnt happen. is problem
            if (bit == -1)
            {
                throw new IOException(
                        "Error reading compressed file. unexepcted end of input. NO PSEUDO_EOF CHARACTER !!!!!");
            } else
            { //survived checks? start walking tree. 0 : left, 1 : right
                node = bit == 0 ? node.getLeft() : node.getRight();
                if (node.isLeaf())
                {
                    int val = node.getValue();
                    if (val == PSEUDO_EOF)
                    {
                        done = true;
                    } else
                    {
                        // node and not pEOF? write self to file
                        output.write(val);
                        node = root;
                        writtenBitCount += BITS_PER_WORD;
                    }
                }
            }

        }
        return writtenBitCount;
    }

    public void setViewer(IHuffViewer viewer)
    {
        myViewer = viewer;
    }

    private void showString(String s)
    {
        if (myViewer != null)
            myViewer.update(s);
    }
}
