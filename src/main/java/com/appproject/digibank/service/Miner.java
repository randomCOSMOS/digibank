package com.appproject.digibank.service;

public class Miner {  
  
    private double reward;    
  
    public void mine(Block block, BlockChain blockChain) {  
  
        while(notGoldenHash(block)) {  
            block.generateHash();  
            block.incrementNonce();  
        }  
  
        System.out.println();
        System.out.println(block+" has just mined...");  
        System.out.println("Hash is: "+block.getHash());          
        blockChain.addBlock(block);  
        reward+=Constants.MINER_REWARD;
    }  
      
    // So miners will generate hash values until they find the right hash.  
    //that matches with DIFFICULTY variable declared in class Constant   
    public boolean notGoldenHash(Block block) {  
        String leadingZeros = new String(new char[Constants.DIFFICULTY]).replace('\0', '0');  
        return !block.getHash().substring (0, Constants.DIFFICULTY).equals (leadingZeros);  
    }  
    
    public double getReward() {  
        return this.reward;  
    }  
  
}  
