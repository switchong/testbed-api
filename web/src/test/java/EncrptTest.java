import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


public class EncrptTest {


    @Disabled
    @Test
    public void test() throws Exception {
        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword("vhdkdnj1234!");      //암호화 키(password)
        jasypt.setAlgorithm("PBEWithMD5AndDES");

        String encryptedText = jasypt.encrypt("localvhdkdnj");    //암호화
        String plainText = jasypt.decrypt(encryptedText);  //복호화

        System.out.println("encryptedText:  " + encryptedText); //암호화된 값
        System.out.println("plainText:  " + plainText);         //복호화된 값
    }
}
