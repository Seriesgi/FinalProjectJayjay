package id.seriesgi.api.steps;

import id.seriesgi.api.ApiClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiStepDefinitions {
    private final ApiClient apiClient = new ApiClient();
    private Response response;
    private String createdUserId;

    @Given("the DummyAPI service is available")
    public void dummyApiIsAvailable() { }

    @When("I request the public tag list")
    public void requestTagList() {
        response = apiClient.getTags();
    }

    @Then("the API response status should be {int}")
    public void verifyStatus(int status) {
        assertThat(response.statusCode(), is(status));
    }

    @Then("the tag response should contain a non-empty data list")
    public void verifyTagData() {
        assertThat(response.jsonPath().getList("data"), is(not(empty())));
        // DummyAPI's first tag can be null or blank. Validate the contract by
        // asserting that the collection contains at least one useful tag.
        assertThat(response.jsonPath().getList("data", String.class),
                hasItem(not(blankOrNullString())));
    }

    @When("I create a DummyAPI user with first name {string} and last name {string}")
    public void createUser(String firstName, String lastName) {
        response = apiClient.createUser(Map.of("firstName", firstName, "lastName", lastName, "email", "qa." + System.currentTimeMillis() + "@example.com"));
        createdUserId = response.jsonPath().getString("id");
    }

    @Then("the created user should have first name {string}")
    public void verifyCreatedUser(String firstName) {
        assertThat(createdUserId, is(not(blankOrNullString())));
        assertThat(response.jsonPath().getString("firstName"), is(firstName));
    }

    @When("I retrieve the created user")
    public void retrieveCreatedUser() {
        response = apiClient.getUserById(createdUserId);
    }

    @Then("the retrieved user should have last name {string}")
    public void verifyRetrievedUser(String lastName) {
        assertThat(response.jsonPath().getString("lastName"), is(lastName));
    }

    @When("I update the created user's first name to {string}")
    public void updateUser(String firstName) {
        response = apiClient.updateUser(createdUserId, Map.of("firstName", firstName));
    }

    @Then("the updated user should have first name {string}")
    public void verifyUpdatedUser(String firstName) {
        assertThat(response.jsonPath().getString("firstName"), is(firstName));
    }

    @When("I delete the created user")
    public void deleteUser() {
        response = apiClient.deleteUser(createdUserId);
    }

    @Then("the deletion response should confirm the user id")
    public void verifyDeletion() {
        assertThat(response.jsonPath().getString("id"), is(createdUserId));
    }
}
