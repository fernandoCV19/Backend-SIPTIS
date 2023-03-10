package BackendSIPTIS.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class ConnectionAWSS3Config {
	@Value("${cloud.aws.credentials.access-key}")
	private String awsId;
	 
	@Value("${cloud.aws.credentials.secret-key}")
	private String awsKey;
		
	@Value("${cloud.aws.region.static}")
	private String region;
	
	@Bean
	public BasicAWSCredentials basicAWSCredentials() {
        	return new BasicAWSCredentials(awsId, awsKey);
	}
	@Bean
	public AmazonS3 amazonS3() {
		final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(awsId, awsKey);
        // Get Amazon S3 client and return the S3 client object
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(region)
                .build();
	}
	
}