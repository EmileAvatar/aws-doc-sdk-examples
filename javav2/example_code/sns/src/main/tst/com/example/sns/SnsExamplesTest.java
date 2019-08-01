package com.example.sns;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.*;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SnsExamplesTest {

    /**
     * The entry point, which results in calls to all test methods.
     *
     * @param args Command line arguments (ignored).
     */
    public static void main(String[] args) {
        SnsExamplesTest tester = new SnsExamplesTest();
        tester.runAllTests();
    }
    final String message = "This was sent from the AWS Java SDK v2";
    final String phoneNumber = "+15555555555";
    final String topicName = "test";
    final String emailForSubscription = "example@example.com";
    final String lambdaArnForSubscription = "";
    final String urlForSubscription = "";

    SnsClient snsClient = SnsClient.builder().region(Region.US_EAST_1).build();
    String topicArn = snsClient.createTopic(CreateTopicRequest.builder().name(topicName).build()).topicArn();
    String subscriptionArn = snsClient.subscribe(SubscribeRequest.builder().protocol("email").endpoint(emailForSubscription).topicArn(topicArn).returnSubscriptionArn(true).build()).subscriptionArn();
    String subscriptionConfirmationToken = "";


    @Test
    public void runAllTests() {
        CheckOptOut_returnsSuccessful();
        ConfirmSubscription_returnsSuccessful();
        CreateTopic_returnsSuccessful();
        DeleteTopic_returnsSuccessful();

    }

    @BeforeEach
    private void setup() {


    }


    @Test
    public void CheckOptOut_returnsSuccessful() {
        //GIVEN
        CheckOptOut checkOptOut = new CheckOptOut();
        String[] args = new String[]{phoneNumber};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        //WHEN - execute the example class and capture the output
        checkOptOut.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "CheckOptOut should print a response");


    }

    @Test
    public void ConfirmSubscription_returnsSuccessful() {
        //GIVEN
        ConfirmSubscription confirmSubscription = new ConfirmSubscription();
        String[] args = new String[]{subscriptionConfirmationToken, topicArn};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        //WHEN - execute the example class and capture the output
        confirmSubscription.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "ConfirmSubscription should print a response");


    }

    @Test
    public void CreateTopic_returnsSuccessful() {
        //GIVEN
        CreateTopic createTopic = new CreateTopic();
        String[] args = new String[]{topicName};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        int totalTopics = snsClient.listTopics(ListTopicsRequest.builder().build()).topics().size();

        //WHEN - execute the example class and capture the output
        createTopic.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        int newTotalTopics = snsClient.listTopics(ListTopicsRequest.builder().build()).topics().size();

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "CreateTopic should print a response");
        assertEquals("CreateTopic should add a new Topic", totalTopics + 1, newTotalTopics);


    }

    @Test
    public void DeleteTopic_returnsSuccessful() {
        //GIVEN
        DeleteTopic deleteTopic = new DeleteTopic();
        String topicArn = snsClient.createTopic(CreateTopicRequest.builder().name(topicName).build()).topicArn();
        String[] args = new String[]{topicArn};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        int totalTopics = snsClient.listTopics(ListTopicsRequest.builder().build()).topics().size();

        //WHEN - execute the example class and capture the output
        deleteTopic.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        int newTotalTopics = snsClient.listTopics(ListTopicsRequest.builder().build()).topics().size();

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "DeleteTopic should print a response");
        assertEquals("DeleteTopic should remove a Topic", totalTopics - 1, newTotalTopics);


    }

    @Test
    public void GetSMSAtrributes_returnsSuccessful() {
        //GIVEN
        GetSMSAtrributes getSMSAtrributes = new GetSMSAtrributes();
        String[] args = new String[]{};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        //WHEN - execute the example class and capture the output
        getSMSAtrributes.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "GetSMSAtrributes should print a response");


    }

    @Test
    public void GetTopicAttributes_returnsSuccessful() {
        //GIVEN
        GetTopicAttributes getTopicAttributes = new GetTopicAttributes();
        String topicArn = snsClient.createTopic(CreateTopicRequest.builder().name(topicName).build()).topicArn();
        String[] args = new String[]{topicArn};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        //WHEN - execute the example class and capture the output
        getTopicAttributes.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "GetTopicAttributes should print a response");


    }

    @Test
    public void ListOptOut_returnsSuccessful() {
        //GIVEN
        ListOptOut listOptOut = new ListOptOut();
        String[] args = new String[]{};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        //WHEN - execute the example class and capture the output
        listOptOut.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "ListOptOut should print a response");


    }

    @Test
    public void ListSubscriptions_returnsSuccessful() {
        //GIVEN
        ListSubscriptions listSubscriptions = new ListSubscriptions();
        String[] args = new String[]{};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        //WHEN - execute the example class and capture the output
        listSubscriptions.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "ListSubscriptions should print a response");


    }

    @Test
    public void ListTopics_returnsSuccessful() {
        //GIVEN
        ListTopics listTopics = new ListTopics();
        String[] args = new String[]{};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        //WHEN - execute the example class and capture the output
        listTopics.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "ListTopics should print a response");


    }

    @Test
    public void PublishTextSMS_returnsSuccessful() {
        //GIVEN
        PublishTextSMS publishTextSMS = new PublishTextSMS();
        String[] args = new String[]{message, phoneNumber};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        //WHEN - execute the example class and capture the output
        publishTextSMS.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "PublishTextSMS should print a response");
    }

    @Test
    public void PublishTopic_returnsSuccessful() {
        //GIVEN
        PublishTopic publishTopic = new PublishTopic();
        String topicArn = snsClient.createTopic(CreateTopicRequest.builder().name(topicName).build()).topicArn();
        String[] args = new String[]{message, topicArn};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        //WHEN - execute the example class and capture the output
        publishTopic.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "PublishTopic should print a response");
    }

    @Test
    public void SetSMSAttributes_returnsSuccessful() {
        //GIVEN
        SetSMSAttributes setSMSAttributes = new SetSMSAttributes();
        String[] args = new String[]{};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        //WHEN - execute the example class and capture the output
        setSMSAttributes.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "SetSMSAttributes should print a response");
    }

    @Test
    public void SetTopicAttributes_returnsSuccessful() {
        //GIVEN
        SetTopicAttributes setTopicAttributes = new SetTopicAttributes();
        String attribute = "DisplayName";
        String value = "Test From AWS SDK for Java v2";
        String topicArn = snsClient.createTopic(CreateTopicRequest.builder().name(topicName).build()).topicArn();
        String[] args = new String[]{attribute, value, topicArn};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        //WHEN - execute the example class and capture the output
        setTopicAttributes.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "SetTopicAttributes should print a response");
    }


    @Test
    public void SubscribeEmail_returnsSuccessful() {

        //GIVEN
        SubscribeEmail subscribeEmail = new SubscribeEmail();
        String[] args = new String[]{topicArn, emailForSubscription};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        //int totalSubscriptions = snsClient.listSubscriptions(ListSubscriptionsRequest.builder().build()).subscriptions().size();

        //WHEN - execute the example class and capture the output
        subscribeEmail.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        //int newTotalSubscriptions = snsClient.listSubscriptions(ListSubscriptionsRequest.builder().build()).subscriptions().size();

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "SubscribeEmail should print a response");
        //assertEquals("SubscribeEmail should add a Subscription", totalSubscriptions + 1, newTotalSubscriptions);
    }

    @Test
    public void SubscribeHTTPS_returnsSuccessful() {
        //GIVEN
        SubscribeHTTPS subscribeHTTPS = new SubscribeHTTPS();
        String[] args = new String[]{topicArn, urlForSubscription};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        int totalSubscriptions = snsClient.listSubscriptions(ListSubscriptionsRequest.builder().build()).subscriptions().size();

        //WHEN - execute the example class and capture the output
        subscribeHTTPS.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        int newTotalSubscriptions = snsClient.listSubscriptions(ListSubscriptionsRequest.builder().build()).subscriptions().size();

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "SubscribeHTTPS should print a response");
        assertEquals("SubscribeHTTPS should add a Subscription", totalSubscriptions + 1, newTotalSubscriptions);
    }

    @Test
    public void SubscribeLambda_returnsSuccessful() {
        //GIVEN
        SubscribeLambda subscribeLambda = new SubscribeLambda();
        String[] args = new String[]{topicArn, lambdaArnForSubscription};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        int totalSubscriptions = snsClient.listSubscriptions(ListSubscriptionsRequest.builder().build()).subscriptions().size();

        //WHEN - execute the example class and capture the output
        subscribeLambda.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        int newTotalSubscriptions = snsClient.listSubscriptions(ListSubscriptionsRequest.builder().build()).subscriptions().size();

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "SubscribeLambda should print a response");
        assertEquals("SubscribeLambda should add a Subscription", totalSubscriptions + 1, newTotalSubscriptions);
    }

    @Test
    public void SubscribeTextSMS_returnsSuccessful() {
        //GIVEN
        SubscribeTextSMS subscribeTextSMS= new SubscribeTextSMS();
        String[] args = new String[]{topicArn, phoneNumber};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        int totalSubscriptions = snsClient.listSubscriptions(ListSubscriptionsRequest.builder().build()).subscriptions().size();

        //WHEN - execute the example class and capture the output
        subscribeTextSMS.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        int newTotalSubscriptions = snsClient.listSubscriptions(ListSubscriptionsRequest.builder().build()).subscriptions().size();

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "SubscribeTextSMS should print a response");
        assertEquals("SubscribeTextSMS should add a Subscription", totalSubscriptions + 1, newTotalSubscriptions);
    }

    @Test
    public void Unsubscribe_returnsSuccessful() {

        //GIVEN
        Unsubscribe unsubscribe = new Unsubscribe();
        String[] args = new String[]{subscriptionArn};
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        int totalSubscriptions = snsClient.listSubscriptions(ListSubscriptionsRequest.builder().build()).subscriptions().size();

        //WHEN - execute the example class and capture the output
        unsubscribe.main(args);

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String output = buffer.toString();
        buffer.reset();
        System.out.println(output);

        int newTotalSubscriptions = snsClient.listSubscriptions(ListSubscriptionsRequest.builder().build()).subscriptions().size();

        //THEN - Check for printed output
        assertTrue(output.length() > 0, "Unsubscribe should print a response");
        assertEquals("Unsubscribe should remove a Subscription", totalSubscriptions - 1, newTotalSubscriptions);
    }


}