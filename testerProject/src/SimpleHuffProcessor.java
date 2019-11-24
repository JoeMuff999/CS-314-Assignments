/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, <NAME1> and <NAME2), this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 (Student whose turnin account is being used)
 *  UTEID:
 *  email address:
 *  Grader name:
 *
 *  Student 2
 *  UTEID:
 *  email address:
 *
 *
 * what to ask:
 * do we need to do pre conditions for our own methods for separate classes?
 * priorityQueue = do i need to generic it? do i need to implement all methods of standard, or just what I need? is collections.sort fine on every call?
 * value of parent nodes? -1?
 * read one a time safer?
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

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
        //        showString("Not working yet");
        //        myViewer.update("Still not working");
        //        throw new IOException("preprocess not implemented");
        preprocessCompressReturnVal = 0;

        mapOfBits = generateMap(in);

        hf = new HuffmanTree(mapOfBits);
        
        chunkMap = hf.getChunkMap();
        
        //is Standard Count Format? if not, its STF
        isSCF = headerFormat == STORE_COUNTS;

        //getting correct return value;
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

    private Map<String, Integer> generateMap(InputStream in) throws IOException
    {

        int inbits = 0;
        int bitCounter = 0;
        StringBuilder currByteBuilder = new StringBuilder();
        Map<String, Integer> mapOfBits = new HashMap<>();

        BitInputStream bits = new BitInputStream(in);
        try
        {
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
        int bitCount = 0;
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

    private int printAsBits(BitOutputStream output)
    {
        return isSCF ? printSCF(output) : printSTF(output);
    }

    private int printSCF(BitOutputStream output)
    {
        int totalBitCount = 0;
        for (int i = 0; i < ALPH_SIZE; i++)
        {
            if (mapOfBits.containsKey("" + i))
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

    private int printSTF(BitOutputStream output)
    {
        int totalBitCount = 0;

        //print 32 bit size
        output.writeBits(BITS_PER_INT, hf.getBitSize());
        totalBitCount += BITS_PER_INT;

        for (Integer i : preOrderTraversal)
        {
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

    private int printCompressedText(InputStream in, BitOutputStream output) throws IOException
    {
        int totalBitCount = 0;
        int inbits = 0;
        BitInputStream bits = new BitInputStream(in);

        while ((inbits = bits.readBits(IHuffConstants.BITS_PER_WORD)) != -1)
        {
            String encodedChunk = chunkMap.get(inbits);
            for (int i = 0; i < encodedChunk.length(); i++)
            {
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
        if (input.readBits(BITS_PER_INT) != MAGIC_NUMBER)
            myViewer.showError("Error reading compressed file. File did not start with the huff magic number.");
        else
        {
            try
            {
                isSCF = input.readBits(BITS_PER_INT) == STORE_COUNTS;
                if (isSCF)
                    hf = rebuildTreeSCF(input);
                else
                    hf = rebuildTreeSTF(input);
                
                resultBits = walkTheTree(input, out);
            } catch (IOException e)
            {
                myViewer.showError("Encountered IOException while uncompressing file");
            }
        }
        
        return resultBits;
    }

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

    private HuffmanTree rebuildTreeSTF(BitInputStream input) throws IOException
    {
        //rebuilding HuffmanTree 
        ArrayList<TreeNode> arr = new ArrayList<>();
        int bitMax = input.readBits(BITS_PER_INT);
        int currBits = 0;
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

            } else if (curr == 1)
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

        return new HuffmanTree(root);
    }

    int treeIndex = 0; //use a second global index so that all recursive calls know the correct index

    private TreeNode buildTree(TreeNode n, ArrayList<TreeNode> arr, int index)
    {
        if (treeIndex == arr.size())
            return null;
        else
        {
            n = arr.get(treeIndex);
            treeIndex++;
            if (n.getValue() == -1)
            {
                n.setLeft(buildTree(n.getLeft(), arr, index));
                n.setRight(buildTree(n.getRight(), arr, index));
            }
        }
        return n;
    }

    private int walkTheTree(BitInputStream input, OutputStream output) throws IOException
    {
        boolean done = false;
        int writtenBitCount = 0;
        TreeNode root = hf.getRoot();
        TreeNode node = root;
        while(!done)
        {
            int bit = input.readBits(ONE_BIT);
            if(bit == -1)
            {
                throw new IOException("Error reading compressed file. unexepcted end of input. NO PSEUDO_EOF CHARACTER !!!!!");
            }
            else
            {
                if(node == null)
                {
                    System.out.println(hf.toString());
                    System.out.println(mapOfBits.toString());
                    System.out.println(chunkMap.toString());
                    System.out.println("+HEELLOOO" + root.toString());
                }
                    
                node = bit == 0 ? node.getLeft() : node.getRight();
                if(node.isLeaf())
                {
                    int val = node.getValue();
                    if(val == PSEUDO_EOF)
                    {
                        done = true;
                    }
                    else
                    {
                        output.write(val);
                        node = root;
                        writtenBitCount+=BITS_PER_WORD;
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
