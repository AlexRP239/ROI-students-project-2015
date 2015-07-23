package edu.roi.playbox.domain.dto;

/**
 * @author apavelchuk
 * @since 20.07.2015.
 */
public class ResultStatus {
    private final String status;
    private final String errorCode;
    private final String errorDescription;

    private ResultStatus(String status, String errorCode, String errorDescription) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public static ResultStatus success() {
        return new ResultStatus("SUCCESS", null, null);
    }

    public static ResultStatus error(String errorCode, String errorDescription) {
        return new ResultStatus("ERROR", errorCode, errorDescription);
    }

    public static ResultStatus error(String errorDescription) {
        return new ResultStatus("ERROR", "UNKNOWN", errorDescription);
    }

}
