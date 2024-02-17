package bloomberg.deals.exception;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The RequestException represents failure during an API call that must be transmitted to the user
 */
@Getter
@RequiredArgsConstructor
public class RequestException extends RuntimeException {
    /**
     * The error message to be transmitted to the user
     */
    final private String ResponseMessage;
}
