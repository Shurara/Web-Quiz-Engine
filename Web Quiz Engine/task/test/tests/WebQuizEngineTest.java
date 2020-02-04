package tests;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import engine.WebQuizEngine;
import org.hyperskill.hstest.v6.mocks.web.request.HttpRequest;
import org.hyperskill.hstest.v6.stage.SpringTest;
import org.hyperskill.hstest.v6.testcase.CheckResult;
import org.hyperskill.hstest.v6.testcase.TestCase;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static tests.TestHelper.*;
import static tests.ApiTester.*;

public class WebQuizEngineTest extends SpringTest {

    public WebQuizEngineTest() {
        super(WebQuizEngine.class, 8889);
    }

    @Override
    public List<TestCase> generate() {
        return Arrays.asList(
            new TestCase<>().setCheckFunc(wrap((r, a) -> checkQuizReceived())),
            new TestCase<>().setCheckFunc(wrap((r, a) -> checkQuizSuccess("2", true))),
            new TestCase<>().setCheckFunc(wrap((r, a) -> checkQuizSuccess("1", false)))
        );
    }

    private CheckResult checkQuizReceived() {
        String url = "/api/quiz";
        HttpResp resp = new HttpResp(get(url).send(), url, "GET");

        checkStatusCode(resp, 200);
        JsonElement json = getJson(resp);

        checkIsObject(resp, json);
        checkObjectKey(resp, json, "title");
        checkObjectKey(resp, json, "text");
        checkObjectKey(resp, json, "options");

        JsonObject obj = json.getAsJsonObject();

        checkIsString(resp, obj.get("title"), "title");
        checkIsString(resp, obj.get("text"), "text");
        checkIsArray(resp, obj.get("options"), "options");

        JsonArray arr = obj.get("options").getAsJsonArray();
        checkArrayLength(resp, arr, 4, "options");

        checkIsString(resp, arr.get(0), "options[0]");
        checkIsString(resp, arr.get(1), "options[1]");
        checkIsString(resp, arr.get(2), "options[2]");
        checkIsString(resp, arr.get(3), "options[3]");

        return CheckResult.TRUE;
    }

    private CheckResult checkQuizSuccess(String answerSent, boolean shouldResponse) {
        String url = "/api/quiz";

        HttpRequest req = TestHelper.post(url, Map.of("answer", answerSent));
        HttpResp resp = new HttpResp(req.send(), url, "POST");

        checkStatusCode(resp, 200);
        JsonElement json = getJson(resp);

        checkIsObject(resp, json);
        checkObjectKey(resp, json, "success");
        checkObjectKey(resp, json, "feedback");

        JsonObject obj = json.getAsJsonObject();

        checkIsBoolean(resp, obj.get("success"), "success");
        checkIsString(resp, obj.get("feedback"), "feedback");

        checkBooleanValue(resp, obj.get("success"), shouldResponse, "success");

        return CheckResult.TRUE;
    }
}
