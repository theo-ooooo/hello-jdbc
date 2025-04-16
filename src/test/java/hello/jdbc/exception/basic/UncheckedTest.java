package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@Slf4j
public class UncheckedTest {

    @Test
    void checked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void checked_throw() {
        Service service = new Service();
        assertThatThrownBy(service::callThrow).isInstanceOf(MyUncheckedException.class);
    }

    static class MyUncheckedException extends RuntimeException {
        public MyUncheckedException(String message) {
            super(message);
        }
    }

    static class Service {
        Repository repository = new Repository();
        /**
         * 예외를 잡아서 처리하는 코드
         */
        public void callCatch() {
            try {
                repository.call();
            }catch(MyUncheckedException e) {
                log.info("예외 처리, message={}", e.getMessage(), e);
            }
        }

        public void callThrow() {
            repository.call();
        }

    }

    static class Repository {
        public void call() {
            throw new MyUncheckedException("ex");
        }
    }
}

