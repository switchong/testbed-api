package com.nftgram.web.member.service;



import org.springframework.stereotype.Service;

@Service
public class JwtService {


    //access token secret key
//    public static final String AT_SECRET_KEY = "STABYTEST_AT";
//    //refresh token secret key
//    private static final String RT_SECRET_KEY = "STABYTEST_RT";
//    private static final Logger LOGGER = LoggerFactory.getLogger(JwtService.class);
//    private static final String DATA_KEY = "user";
//
//
//    @Autowired
//    private NftMemberRepository nftMemberRepository;
//
//    @Autowired
//    private TokenRepository tokenRepository;
//
//
//    public TokenAuthDto createTokenSet(NftMemberDto nftMemberDto) {
//        long curTime = System.currentTimeMillis();
//
//        String refreshToken = Jwts.builder()
//                .setHeaderParam("typ", "JWT")
//                .setExpiration(new Date(curTime + (1000*60*60*24*14)))
//                .setIssuedAt(new Date(curTime))
//                .claim(DATA_KEY, nftMemberDto)
//                .signWith(SignatureAlgorithm.HS256, this.generateKey(RT_SECRET_KEY))
//                .compact();
//
//        String accessToken = Jwts.builder()
//                .setHeaderParam("typ", "JWT")
//                .setExpiration(new Date(curTime + (1000*60*30)))
//                .setIssuedAt(new Date(curTime))
//                .claim(DATA_KEY, nftMemberDto)
//                .signWith(SignatureAlgorithm.HS256, this.generateKey(AT_SECRET_KEY))
//                .compact();
//
//
//        NftMember nftMember = nftMemberRepository.findByNftMemberUserId(nftMemberDto.getNftMemberUserId());
//        Token token = tokenRepository.findByUserId(nftMember.getNftMemberId());
//
//        if (token != null) {
//            token.refreshUpdate(refreshToken);
//        } else {
//            Token newToken = new Token(refreshToken, nftMember);
//            tokenRepository.save(newToken);
//        }
//
//        return new TokenAuthDto(refreshToken, accessToken);
//    }
//
//    public TokenAuthDto refreshAccessToken(String refreshToken) {
//        long curTime = System.currentTimeMillis();
//        //refreshToken의 만료기간이 남았는지 확인하고,
//        if(!isValidToken(refreshToken, RT_SECRET_KEY)) {
//            throw new AuthenticationException("로그인되어있지 않습니다.");
//        }
//
//        //DB로부터 refreshToken 유효한지 조회
//        Token validToken = tokenRepository.findByRefreshToken(refreshToken);
//        if(validToken == null) {
//            throw new AuthenticationException("로그인되어있지 않습니다.");
//        }
//
//        Jws<Claims> claims = null;
//        try {
//            claims = Jwts.parser().setSigningKey(this.generateKey(RT_SECRET_KEY)).parseClaimsJws(refreshToken);
//        } catch (Exception e) {
//            throw new JWTException("decodeing failed");
//        }
//
//        //curTime이 refreshToken의 만료일 7일 이내면, refreshTokenSet 진행.
//        if(Long.parseLong(String.valueOf(claims.getBody().get("exp"))) * 1000 - curTime <= (1000*60*60*24*7)) {
//            return refreshTokenSet(refreshToken);
//        }
//
//        String accessToken = Jwts.builder()
//                .setHeaderParam("typ", "JWT")
//                .setExpiration(new Date(curTime + (1000*60*30)))
//                .setIssuedAt(new Date(curTime))
//                .claim(DATA_KEY, getUser(refreshToken, RT_SECRET_KEY))
//                .signWith(SignatureAlgorithm.HS256, this.generateKey(AT_SECRET_KEY))
//                .compact();
//
//        return new TokenAuthDto(refreshToken, accessToken);
//    }
//
//    public TokenAuthDto refreshTokenSet(String refreshToken) {
//        return createTokenSet(getUser(refreshToken, RT_SECRET_KEY));
//    }
//
//    private byte[] generateKey(String secretKey) {
//        byte[] key = null;
//        try {
//            key = secretKey.getBytes("UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            LOGGER.error("Making secret Key Error :: ", e);
//        }
//
//        return key;
//    }
//
//    public NftMemberDto getUser(String jwt, String secretKey) {
//        Jws<Claims> claims = null;
//        try {
//            claims = Jwts.parser().setSigningKey(this.generateKey(secretKey)).parseClaimsJws(jwt);
//        } catch (Exception e) {
//            LOGGER.debug(e.getMessage(), e);
//            throw new JWTException("decodeing failed");
//        }
//        return new ObjectMapper().convertValue(claims.getBody().get(DATA_KEY), NftMemberDto.class);
//    }
//
//    public boolean isValidToken(String jwt, String secretKey) {
//        try {
//            Jwts.parser().setSigningKey(this.generateKey(secretKey)).parseClaimsJws(jwt);
//        } catch (Exception e) {
//            return false;
//        }
//        return true;
//    }
}
