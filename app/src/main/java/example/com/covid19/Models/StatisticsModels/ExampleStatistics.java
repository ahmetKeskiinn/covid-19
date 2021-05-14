package example.com.covid19.Models.StatisticsModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExampleStatistics {
    @SerializedName("get")
    @Expose
    private String get;
    @SerializedName("parameters")
    @Expose
    private Parameters parameters;
    @SerializedName("errors")
    @Expose
    private List<Object> errors = null;
    @SerializedName("results")
    @Expose
    private Integer results;
    @SerializedName("response")
    @Expose
    private List<ResponseStatistics> response = null;

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
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

    public List<ResponseStatistics> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseStatistics> response) {
        this.response = response;
    }

}