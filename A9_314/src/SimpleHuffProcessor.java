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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SimpleHuffProcessor implements IHuffProcessor {

    private IHuffViewer myViewer;
    
    private Map<Integer, String> chunkMap; //maps a given ASCII key to its tree-traversal-code 
    private Map<String, Integer> mapOfBits; //maps a given chunk (stored as string of length 8), to its frequency
    
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
    public int preprocessCompress(InputStream in, int headerFormat) throws IOException {
//        showString("Not working yet");
//        myViewer.update("Still not working");
//        throw new IOException("preprocess not implemented");
       
        
        mapOfBits = generateMap(in);           
        
        HuffmanTree hf = new HuffmanTree(mapOfBits);
        chunkMap = hf.getChunkMap();
        
        //is Standard Count Format? if not, its STF
        isSCF = headerFormat == STORE_COUNTS;
        
        return 0;
    }
    
    private Map<String, Integer> generateMap(InputStream in) throws IOException
    {
        
        int inbits = 0;
        int bitCounter = 0;
        StringBuilder currByteBuilder = new StringBuilder();
        Map<String, Integer> mapOfBits = new TreeMap<>();
        
        BitInputStream bits = new BitInputStream(in);
        while ((inbits = bits.readBits(IHuffConstants.BITS_PER_WORD)) != -1) {
            String currByte = "" + inbits;
            if(mapOfBits.containsKey(currByte))
            {
                int currVal = mapOfBits.get(currByte);
                mapOfBits.put(currByte, currVal + 1);
            }
            else
            {
                mapOfBits.put(currByte, 1);
            }
        }
        //add PEFO
        mapOfBits.put("" + PSEUDO_EOF, 1);
        //reads one bit a time. ask about this
//        while ((inbits = bits.readBits(1)) != -1){
//            
//            if(bitCounter == BITS_PER_WORD)
//            {
//                  String currByte = currByteBuilder.toString();
////                int charCode = Integer.parseInt(currByte,2);
////                String s = new Character((char) charCode).toString();
////                System.out.print(charCode + " " + s + currByte + "|| ");
//                
//                if(mapOfBits.containsKey(currByte))
//                {
//                    int currVal = mapOfBits.get(currByte);
//                    mapOfBits.put(currByte, currVal + 1);
//                }
//                else
//                {
//                    mapOfBits.put(currByte, 1);
//                }
//                currByteBuilder = new StringBuilder();
//                bitCounter = 0;                
//            }                
//            currByteBuilder.append(inbits);
//            bitCounter++;
//        }
        
        bits.close();
        return mapOfBits;
    }

    /**
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
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
        int bitCount = 0;
        bitCount += printHeaderConstants(out);
        bitCount += printAsBits(out);
        bitCount += printCompressedText(in,out);
        
        return bitCount;
    }
    
    private int printHeaderConstants(OutputStream out)
    {
        BitOutputStream output = new BitOutputStream(out);
        //MAGIC_NUMBER
        output.writeBits(BITS_PER_INT, MAGIC_NUMBER);
        //SCF or STF
        if(isSCF)
            output.writeBits(BITS_PER_INT, STORE_COUNTS);
        else
            output.writeBits(BITS_PER_INT, STORE_TREE);
        
        return BITS_PER_INT * 2;
            
    }
    
    private int printAsBits(OutputStream out)
    {
        int totalBitCount = 0;
        BitOutputStream output = new BitOutputStream(out);
        for(int i = 0; i < ALPH_SIZE; i++)
        {
            if(mapOfBits.containsKey("" + i))
            {
                output.writeBits(BITS_PER_INT, mapOfBits.get("" + i));
            }
            else
            {
                
                output.writeBits(BITS_PER_INT, 0);
            }
            totalBitCount+=BITS_PER_INT;
        }
        return totalBitCount;
    }
    
    private int printCompressedText(InputStream in, OutputStream out) throws IOException
    {
        int totalBitCount = 0;
        int inbits = 0;
        BitInputStream bits = new BitInputStream(in);
        BitOutputStream output = new BitOutputStream(out);
        
        while ((inbits = bits.readBits(IHuffConstants.BITS_PER_WORD)) != -1) {
            String encodedChunk = chunkMap.get(inbits);
            for(int i = 0; i < encodedChunk.length(); i++)
            {
                int chunk = encodedChunk.charAt(i);
                output.writeBits(1, chunk);
                totalBitCount++;
            }
        }
        //write PEOF
        String pEOF = chunkMap.get(PSEUDO_EOF);
        for(int i = 0; i < pEOF.length(); i++)
        {
            output.writeBits(1,pEOF.charAt(i));
        }
        bits.close();
        output.close();
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
    public int uncompress(InputStream in, OutputStream out) throws IOException {
	        throw new IOException("uncompress not implemented");
	        //return 0;
    }

    public void setViewer(IHuffViewer viewer) {
        myViewer = viewer;
    }



    private void showString(String s){
        if(myViewer != null)
            myViewer.update(s);
    }
}
