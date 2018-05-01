package org.bitcoinj.core;

import java.math.BigInteger;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Hash Engineering Solutions
 * Date: 5/3/14
 * To change this template use File | Settings | File Templates.
 */
public class CoinDefinition {


    public static final String coinName = "ION";
    public static final String coinTicker = "ION";
    public static final String coinURIScheme = "ion";

//    public static final String cryptsyMarketId = "0";
//    public static final String cryptsyMarketCurrency = "ION";
    public static final String PATTERN_PRIVATE_KEY_START_UNCOMPRESSED = "[7]";
    public static final String PATTERN_PRIVATE_KEY_START_COMPRESSED = "[X]";

    // To decrease granularity of timestamp
    // Supposed to be 2^n-1
    public static final int stakeTimestampMask = 15; // stake.h STAKE_TIMESTAMP_MASK
    public static final long futureDrift = 16200;
    public static final long minerMiliSleep = 500; // TODO
    //common to all coins
    public static final int bulgarianConst = 128;
    //main.cpp#L2822 pchMessageStart[4] = { 0xc4, 0xe1, 0xd8, 0xec };
    public static final long packetMagic = 0xc4e1d8ec;
    public static final String checkpoint0 = "0000004cf5ffbf2e31a9aa07c86298efb01a30b8911b80af7473d1114715084b";
    public static final String blackAlertSigningKey = "040fd972dba056779d9f998cba8d5866e47fb875fd8cb9c4d36baf88db738a6ffbc581e0fad7f2f129c7f814d81baeda567a3735aaf0bfbc339f40359d4a52b4bf";
    public static final int ionv = 0x0488B21E;
    public static final int ionp = 0x0488ADE4;
    public static final int stakeMinConfirmations = 60; // main.cpp // TODO check
    public static final long dust = 546;

    public enum CoinPrecision {
        Coins,
        Millicoins,
    }
//    public static final CoinPrecision coinPrecision = CoinPrecision.Coins;

    public static final String UNSPENT_API_URL = "https://chainz.cryptoid.info/ion/api.dws?q=unspent";

    public enum UnspentAPIType {
        BitEasy,
        Blockr,
        Abe,
        Cryptoid,
    };
    public static final UnspentAPIType UnspentAPI = UnspentAPIType.Cryptoid;

    public static final String BLOCKEXPLORER_BASE_URL_PROD = "https://chainz.cryptoid.info/ion/";
    public static final String BLOCKEXPLORER_ADDRESS_PATH = "address.dws?";             //blockr.io path
    public static final String BLOCKEXPLORER_TRANSACTION_PATH = "tx.dws?";              //blockr.io path
    public static final String BLOCKEXPLORER_BLOCK_PATH = "block.dws?";                 //blockr.io path
    public static final String BLOCKEXPLORER_BASE_URL_TEST = "https://testnet.chainz.cryptoid.info/ion/";

    public static final String DONATION_ADDRESS = "ikna4mSLeUJP5fRuKwNBCp5x54yb5wbEXQ";  //Hash Engineering donation DASH address

    enum CoinHash {
        SHA256,
        scrypt,
        x11
    };

//    public static final CoinHash coinPOWHash = CoinHash.x11;

//    public static boolean checkpointFileSupport = true;

    public static final int targetTimespan = (int)(2 * 60);  //main.cpp#L988 nTargetTimespan = 2 * 60; // per difficulty cycle, on average.
    public static final int targetSpacing = 64;     //proofs.h#L19 nTargetSpacing = 1 * 60;
    public static final int targetSpacing2 = 64;    // ^ main.h#L97~
    public static final int interval = 10; //targetTimespan / targetSpacing;

    //main.cpp#L48 nCoinbaseMaturity = 500
    public static int spendableCoinbaseDepth = 60; //proofs.cpp: DetermineCoinbaseMaturity
    public static final long maxCoins = 21000000;                 //main.h:  MAX_MONEY
    public static final int maxBlockSize = 20000000;          // main.h MAX_BLOCK_SIZE

    public static final long minTxFee = Coin.CENT.divide(10).longValue(); // TODO FIXME main.h#L53 MIN_TX_FEE 0.01
//    public static final long DUST_LIMIT = Coin.valueOf(1000).longValue(); //main.h CTransaction::GetMinFee
    public static final long INSTANTX_FEE = Coin.CENT.longValue(); // TODO Is never used in wallet code, hardcoded instead
    //
    // Ion
    //

    public static final int protocolVersion = 95700;
    public static final int minProtocolVersion = 95700; // MIN_PEER_PROTO_VERSION
    public static final long blockVersion = 8;
    public static final int protocolV1RetargetingFixed = 0; // main.h
    public static final long txTimeProtocolV3 = 0; // main.h // TODO ?

    public static final String localNode = Utils.isAndroidRuntime() ? "10.0.2.2" : "127.0.0.1";

//    public static final boolean supportsBloomFiltering = true; //Requires protocolVersion 70000 in the client

    //
    //  Production
    //
//    public static final boolean allowBitcoinPrivateKey = false; //for backward compatibility with previous versions
//    public static final int dumpedPrivateKeyHeader = 128;   //common to all coins
//    public static final long oldPacketMagic = 0xfbc0b6db;      //0xfb, 0xc0, 0xb6, 0xdb
//    public static final long PacketMagic = 0xbff41ab6;


    public static int minBroadcastConnections = 0;   //0 for default; we need more peers.

    // TODO
    public static int subsidyDecreaseBlockCount = 525600;  // TODO FIXME !

    public static BigInteger proofOfWorkLimit = Utils.decodeCompactBits(0x1e0fffffL);  //main.cpp bnProofOfWorkLimit (~uint256(0) >> 20);
    public static BigInteger testnetProofOfWorkLimit = Utils.decodeCompactBits(0x1e00ffffL);

    public static BigInteger proofOfStakeLimit = Utils.decodeCompactBits(0x1e00ffffL);  //main.cpp bnProofOfWorkLimit (~uint256(0) >> 20);
    public static BigInteger testnetProofOfStakeLimit = Utils.decodeCompactBits(0x1e00ffffL);


    public static BigInteger maxTarget  = Utils.decodeCompactBits(0x1e0fffffL); //0x1e00ffffL// POW limit   // TODO Not used?

    /** The string returned by getId() for the main, production network where people trade things. */
    public static final String ID_MAINNET = "com.ionomy.production";
    /** The string returned by getId() for the testnet. */
    public static final String ID_TESTNET = "com.ionomy.test";
    /** Unit test network. */
    public static final String ID_UNITTESTNET = "com.ionomy.unittest";
    /** Regtest network if any */
    public static final String ID_REGTESTNET = "com.ionomy.regtest";


    //
    // MAINNET
    //
    public static final int port = 12700; // chainparams

    //Genesis Block Information from main.cpp: LoadBlockIndex
    ///main.cpp#L2521 nBits=1e0fffff
    public static final long genesisDifficultyTarget = (0x1e00ffffL);

    //clientmodel.cpp#L56 fromTime_t(1458750507L); // Genesis block's genesisBlockTime
    static public long genesisBlockTime = 1486045800L;                       //main.cpp: LoadBlockIndex
    //main.cpp#L2521 nNonce=164482
    static public long genesisBlockNonce = (28884498L);                         //main.cpp: LoadBlockIndex

    //base58.h#L279 PUBKEY_ADDRESS = 25
    public static final int addressHeader = 103;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS
    public static final int p2shHeader = 88;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS
    static public String genesisHash = "0000004cf5ffbf2e31a9aa07c86298efb01a30b8911b80af7473d1114715084b"; //main.cpp: hashGenesisBlock
    static public String genesisMerkleRoot = "7af2e961c5262cb0411edcb7414ab7178133fc06257ceb47d349e4e5e35e2d40"; // ? or is this block 1 TODO
    // TODO Testnet ^^
//    static public int genesisBlockValue = 10900000;                                                              //main.cpp: LoadBlockIndex
    //taken from the raw data of the block explorer
//    static public String genesisTxInBytes = "04ffff001d01044c5957697265642030392f4a616e2f3230313420546865204772616e64204578706572696d656e7420476f6573204c6976653a204f76657273746f636b2e636f6d204973204e6f7720416363657074696e6720426974636f696e73";   //"limecoin se convertira en una de las monedas mas segura del mercado, checa nuestros avances"
//    static public String genesisTxOutBytes = "040184710fa689ad5023690c80f3a49c8f13f8d45b8c857fbcbc8bc4a8e4d3eb4b10f4d4604fa08dce601aaf0f470216fe1b51850b4acf21b179c45070ac7b03a9";

    //net.cpp strDNSSeed
    static public String[] dnsSeeds = new String[] {
            "main.seeder.baseserv.com",
            "main.seeder.uksafedns.net"
    };


    //
    // TestNet
    // TODO FIXME all
    public static final int testPort = 51002;     //protocol.h GetDefaultPort(testnet=true)
    public static String testBlackAlertSigningKey = ""; // TODO
    public static final boolean supportsTestNet = true;
    public static final int testnetAddressHeader = 127;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS_TEST
    public static final int testnetp2shHeader = 196;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS_TEST
    public static final long testnetPacketMagic = 0x5ff21530;      //0x5ff21530
//    public static final String testnetGenesisHash = "0000070638e1fb122fb31b4753a5311f3c8784604d9f6ce42e8fec96d94173b4";
    public static final String testnetGenesisHash = "0000070638e1fb122fb31b4753a5311f3c8784604d9f6ce42e8fec96d94173b4"; // scrypt
    static public long testnetGenesisBlockDifficultyTarget = (0x1f00ffffL);         //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockTime = 1458750507L;                       //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockNonce = (72686);                        //main.cpp: LoadBlockIndex
    static public final String testnetGenesisMerkleroot = "a1de9df44936bd1dd483e217fa17ec1881d2caf741ca67a33f6cd6850183078c";                        //main.cpp: LoadBlockIndex
    //public static BigInteger testnetProofOfWorkLimit = Utils.decodeCompactBits(0x1f00ffffL);  //main.cpp bnProofOfWorkLimit (~uint256(0) >> 20);


    static public String[] testnetDnsSeeds = new String[] {
        "test.seed.ionomy.nl",
    };


    //
    // Unit Test Information
    //
    public static final String UNITTEST_ADDRESS = "";
    public static final String UNITTEST_ADDRESS_PRIVATE_KEY = "";

    // TODO FIXME
    //main.cpp GetBlockValue(height, fee)
    public static final Coin GetBlockReward(int height)
    {
        int COIN = 1;
        Coin nSubsidy = Coin.valueOf(0, 0);
        if (height >= 1000)
            nSubsidy = Coin.valueOf(230000, 0);
        return nSubsidy;
    }

    //checkpoints.cpp Checkpoints::mapCheckpoints
    public static void initCheckpoints(Map<Integer, Sha256Hash> checkpoints)
    {
        // TODO
        // checkpoints.put(  11111, Sha256Hash.wrap("c195054e4ee5b2db531cb8f4c2a336a15ca0969406709f4930297d88e825cbb6"));
//        (0,     Params().HashGenesisBlock())
//        (1,     uint256("0x000000ed2f68cd6c7935831cc1d473da7c6decdb87e8b5dba0afff0b00002690") ) // Premine
//        (10,    uint256("0x00000032f5a96d31d74b380c0336445baccb73a01bdbedec868283019bad7016") )  // Confirmation of Premine
//        (22,    uint256("0x00000002e04f91402d78b84433ec744aacac5c40952b918fe09a7d623ac33967") )
//        (32,    uint256("0x0000001880da8fd09cc6f5e93315135fe686eb49f9054c807fa810d56ebb013b") )
//        (35,    uint256("0x0000000af6204fd43bb9cafea1dd192c245979d4dd7bde19efb92f633589ade5") )
//        (45, uint256("0x00000006d6b9e9fba4dee10bc63ca7ea764c80c2b9c4fa6ddedb944eb288a371") )
    }

//    public static final int getInterval(int height, boolean testNet) {
//            return interval;
//    }
//    public static final int getIntervalCheckpoints() {
//        return interval;
//
//    }
//    public static final int getTargetTimespan(int height, boolean testNet) {
//        return targetTimespan;
//    }
}
