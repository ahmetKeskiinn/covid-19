package example.com.covid19.Models.CountryModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExampleResponse {

    @SerializedName("get")
    @Expose
    private String get;
    @SerializedName("parameters")
    @Expose
    private List<Object> parameters = null;
    @SerializedName("errors")
    @Expose
    private List<Object> errors = null;
    @SerializedName("results")
    @Expose
    private Integer results;
    @SerializedName("response")
    @Expose
    private List<String> response = null;

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public void setParameters(List<Object> parameters) {
        this.parameters = parameters;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public Integer getResults() {
        return results;
    }

    public void setResults(Integer results) {
        this.results = results;
    }

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String> response) {
        this.response = response;
    }

}