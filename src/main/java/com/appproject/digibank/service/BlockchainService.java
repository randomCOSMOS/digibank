package com.appproject.digibank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appproject.digibank.model.Transaction;
import com.appproject.digibank.repository.BlockchainRepo;

@Service
public class BlockchainService {
    BlockChain blockChain = new BlockChain();  
    Miner miner = new Miner();  

    @Autowired
    BlockchainRepo blockchainRepo;

    public Transaction mineBlocks(Transaction transaction){
        String prevHash = blockChain.size() == 0 ? Constants.GENESIS_PREV_HASH : blockChain.getBlockChain().get(blockChain.size() - 1).getHash();

        Block block = new Block(blockChain.size(), "transaction", prevHash);  
        miner.mine(block, blockChain);  

        System.out.println("\n" + "BLOCKCHAIN:\n" + blockChain);  
        System.out.println("Miner's reward: " + miner.getReward());

        transaction.setBlock(block.toString());
        transaction.setDate(block.getTime());
        return blockchainRepo.save(transaction);
    }

    public List<Transaction> getTransactionByEmail(String email){
        return blockchainRepo.findByEmail(email);
    }
}
