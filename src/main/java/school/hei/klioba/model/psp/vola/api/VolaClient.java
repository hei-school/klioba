package school.hei.klioba.model.psp.vola.api;

import static org.springframework.web.util.UriUtils.encodeQueryParam;

import java.nio.charset.StandardCharsets;
import school.hei.klioba.model.psp.PspType;
import school.hei.klioba.model.psp.vola.api.gen.client.ApiClient;
import school.hei.klioba.model.psp.vola.api.gen.client.api.PaymentControllerApi;
import school.hei.klioba.model.psp.vola.api.gen.client.model.Payment;

public class VolaClient {
  private final String apiKey;
  private final PaymentControllerApi paymentControllerApi;

  public VolaClient(String baseUrl, String apiKey) {
    this.apiKey = apiKey;
    var apiClient = new ApiClient();
    apiClient.setBasePath(baseUrl);
    this.paymentControllerApi = new PaymentControllerApi(apiClient);
  }

  private static String encodeScope(String scope) {
    return scope != null ? encodeQueryParam(scope, StandardCharsets.UTF_8) : null;
  }

  public Payment create(PspType pspType, String pspId, String email, String scope) {
    return paymentControllerApi.createPayment(apiKey, email, pspType.toString(), pspId, scope);
  }

  public Payment get(PspType pspType, String pspId, String email) {
    return paymentControllerApi.getPayment(apiKey, email, pspType.toString(), pspId);
  }
}
