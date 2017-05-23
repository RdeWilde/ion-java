/*
 * Copyright 2013 Google Inc.
 * Copyright 2014 Andreas Schildbach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bitcoinj.params;

import org.bitcoinj.core.*;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;

import java.math.BigInteger;

/**
 * Parameters for the testnet, a separate public instance of Bitcoin that has relaxed rules suitable for development
 * and testing of applications and new Bitcoin versions.
 */
public class TestNet3Params extends AbstractBitcoinNetParams {
    public TestNet3Params() {
        super();
        id = ID_TESTNET;

        // Genesis hash is

        packetMagic = CoinDefinition.testnetPacketMagic;
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        maxTarget = Utils.decodeCompactBits(CoinDefinition.testnetGenesisBlockDifficultyTarget);
        port = CoinDefinition.testPort;
        addressHeader = CoinDefinition.testnetAddressHeader;
        p2shHeader = CoinDefinition.testnetp2shHeader;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        dumpedPrivateKeyHeader = CoinDefinition.bulgarianConst + CoinDefinition.testnetAddressHeader;
        genesisBlock.setTime(CoinDefinition.testnetGenesisBlockTime);
        genesisBlock.setDifficultyTarget(CoinDefinition.testnetGenesisBlockDifficultyTarget);
        genesisBlock.setNonce(CoinDefinition.testnetGenesisBlockNonce);
        spendableCoinbaseDepth = CoinDefinition.spendableCoinbaseDepth;
        subsidyDecreaseBlockCount = CoinDefinition.subsidyDecreaseBlockCount;
        String genesisHash = genesisBlock.getHashAsString();
//        checkState(genesisHash.equals(CoinDefinition.testnetGenesisHash)); // TODO testcheckpoint[0]
        alertSigningKey = Utils.HEX.decode(CoinDefinition.testBlackAlertSigningKey);

        dnsSeeds = CoinDefinition.testnetDnsSeeds;

        addrSeeds = null;
        bip32HeaderPub = CoinDefinition.ionv; //The 4 byte header that serializes in base58 to "ionv".
        bip32HeaderPriv = CoinDefinition.ionp; //The 4 byte header that serializes in base58 to "ionp"
    }

    private static TestNet3Params instance;
    public static synchronized TestNet3Params get() {
        if (instance == null) {
            instance = new TestNet3Params();
        }
        return instance;
    }

    @Override
    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_TESTNET;
    }

    // February 16th 2012
    //private static final Date testnetDiffDate = new Date(1329264000000L);


    @Override
    public void checkDifficultyTransitions(final StoredBlock storedPrev, final Block nextBlock,
                                           final BlockStore blockStore) throws VerificationException, BlockStoreException {
        // TODO FIXME verifyDifficulty(getNextTargetRequired(storedPrev, blockStore, nextBlock.isStake()), nextBlock);
    }


    // TODO move to block or blockstore?
    public StoredBlock getPrevBlock(StoredBlock currentBlock, boolean isCoinstake, final BlockStore blockStore) throws BlockStoreException {
        if (currentBlock.getHeader() != null && currentBlock.getHeader().getPrevBlockHash().equals(Sha256Hash.ZERO_HASH)) {
            return currentBlock;
        }

        StoredBlock prevBlock = currentBlock.getPrev(blockStore);
        while (prevBlock != null && prevBlock.getHeader() != null && !prevBlock.getHeader().getPrevBlockHash().equals(Sha256Hash.ZERO_HASH)) {
            if (prevBlock.getHeader() != null && prevBlock.getHeader().isStake() == isCoinstake)
                return prevBlock;

            prevBlock = prevBlock.getPrev(blockStore);
        }

        return prevBlock;
    }


    public BigInteger getProofOfStakeLimit(int nHeight) {
        // Always v2
        if (!getId().equals(ID_MAINNET))
            return CoinDefinition.testnetProofOfStakeLimit;

        return CoinDefinition.proofOfStakeLimit;
    }


    @Override
    public BigInteger getNextTargetRequired(StoredBlock pindexLast, final BlockStore blockStore) throws BlockStoreException {
        return this.getNextTargetRequired(pindexLast, blockStore, false);
    }


    public BigInteger getNextTargetRequired(StoredBlock pindexLast, final BlockStore blockStore, final boolean isCoinstake) throws BlockStoreException {

        boolean isTestnet = !getId().equals(ID_MAINNET); // TODO testnet vs mainnet
        BigInteger targetLimit = !isCoinstake  ? (isTestnet ? CoinDefinition.testnetProofOfWorkLimit : CoinDefinition.proofOfWorkLimit) : getProofOfStakeLimit(pindexLast.getHeight()); // TODO testnet vs mainnet
        // TODO mainnet has diff limit for work vs stake?



//  		StoredBlock storedPrevPrev = blockStore.get(prevBlock.getPrevBlockHash());
//  		Block prevPrevBlock = storedPrevPrev.getHeader();

//        Block lastBlock = pindexLast.getHeader();
//        if (lastBlock.isStake() != isCoinstake)
//            if ((pindexLast = getPrevBlock(pindexLast, isCoinstake, blockStore)) != null)
//                lastBlock = pindexLast.getHeader();
// TODO FIXME NullException
//        java.lang.NullPointerException: Attempt to invoke virtual method 'int org.bitcoinj.core.StoredBlock.getHeight()' on a null object reference
//        at org.bitcoinj.params.TestNet3Params.getNextTargetRequired(TestNet3Params.java:125) ~[na:0.0]
//        at org.bitcoinj.params.TestNet3Params.checkDifficultyTransitions(TestNet3Params.java:86) ~[na:0.0]
//        at org.bitcoinj.core.AbstractBlockChain.add(AbstractBlockChain.java:419) ~[na:0.0]

        if (pindexLast != null && pindexLast.getHeader().isStake() != isCoinstake)
            pindexLast = getPrevBlock(pindexLast, isCoinstake, blockStore);

        if (pindexLast == null || pindexLast.getHeader().getPrevBlockHash().equals(Sha256Hash.ZERO_HASH))
            return targetLimit;

        StoredBlock storedPrev = getPrevBlock(pindexLast, isCoinstake, blockStore);

        if (storedPrev == null || storedPrev.getHeader().getPrevBlockHash().equals(Sha256Hash.ZERO_HASH))
            return targetLimit;

        StoredBlock storedPrevPrev = getPrevBlock(storedPrev, isCoinstake, blockStore);

//        if (storedPrevPrev == null || storedPrevPrev.getHeader().getPrevBlockHash().equals(Sha256Hash.ZERO_HASH))
//            return targetLimit;

        int targetSpacing = CoinDefinition.targetSpacing2;

        //int64_t nActualSpacing = pindexPrev->GetBlockTime() - pindexPrevPrev->GetBlockTime();
        long actualSpacing = (pindexLast.getHeader().getTimeSeconds() - storedPrev.getHeader().getTimeSeconds());

        if  (pindexLast.getHeight() > CoinDefinition.protocolV1RetargetingFixed || isTestnet) {
            if (actualSpacing < 0)
                actualSpacing = targetSpacing;
        }

        //nTime > 1444028400;
        if (pindexLast.getHeader().getTimeSeconds() > CoinDefinition.txTimeProtocolV3 || isTestnet) {
            if (actualSpacing > targetSpacing * 10)
                actualSpacing = targetSpacing * 10;
        }

        // ppcoin: target change every block
        // ppcoin: retarget with exponential moving toward target spacing
        //bnNew.SetCompact(pindexPrev->nBits);
        BigInteger newDifficulty = Utils.decodeCompactBits(pindexLast.getHeader().getDifficultyTarget());

        // int64_t nInterval = nTargetTimespan / nTargetSpacing;
        int interval = CoinDefinition.targetTimespan / targetSpacing;


        //bnNew *= ((nInterval - 1) * nTargetSpacing + nActualSpacing + nActualSpacing);
        //bnNew /= ((nInterval + 1) * nTargetSpacing);
        long multiplier = ((interval - 1) * targetSpacing + actualSpacing + actualSpacing);
        long divider = ((interval + 1)  * targetSpacing);
        newDifficulty = newDifficulty.multiply(BigInteger.valueOf(multiplier));
        newDifficulty = newDifficulty.divide(BigInteger.valueOf(divider));

        if (newDifficulty.compareTo(BigInteger.ZERO) <= 0
                || newDifficulty.compareTo(targetLimit) > 0){
            return targetLimit;
        }
        else
            return newDifficulty;
    }

//    @Override
//    public void checkDifficultyTransitions(final StoredBlock storedPrev, final Block nextBlock,
//        final BlockStore blockStore) throws VerificationException, BlockStoreException {
//        if (!isDifficultyTransitionPoint(storedPrev) && nextBlock.getTime().after(testnetDiffDate)) {
//            Block prev = storedPrev.getHeader();
//
//            // After 15th February 2012 the rules on the testnet change to avoid people running up the difficulty
//            // and then leaving, making it too hard to mine a block. On non-difficulty transition points, easy
//            // blocks are allowed if there has been a span of 20 minutes without one.
//            final long timeDelta = nextBlock.getTimeSeconds() - prev.getTimeSeconds();
//            // There is an integer underflow bug in bitcoin-qt that means mindiff blocks are accepted when time
//            // goes backwards.
//            if (timeDelta >= 0 && timeDelta <= NetworkParameters.targetSpacing * 2) {
//        	// Walk backwards until we find a block that doesn't have the easiest proof of work, then check
//        	// that difficulty is equal to that one.
//        	StoredBlock cursor = storedPrev;
//        	while (!cursor.getHeader().equals(getGenesisBlock()) &&
//                       cursor.getHeight() % getInterval() != 0 &&
//                       cursor.getHeader().getDifficultyTargetAsInteger().equals(getMaxTarget()))
//                    cursor = cursor.getPrev(blockStore);
//        	BigInteger cursorTarget = cursor.getHeader().getDifficultyTargetAsInteger();
//        	BigInteger newTarget = nextBlock.getDifficultyTargetAsInteger();
//        	if (!cursorTarget.equals(newTarget))
//                    throw new VerificationException("Testnet block transition that is not allowed: " +
//                	Long.toHexString(cursor.getHeader().getDifficultyTarget()) + " vs " +
//                	Long.toHexString(nextBlock.getDifficultyTarget()));
//            }
//        } else {
//            super.checkDifficultyTransitions(storedPrev, nextBlock, blockStore);
//        }
//    }

}
