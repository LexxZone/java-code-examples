package ru.stateofmind.codeexamples;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

class ParametrizedTestsExample {
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    /**
     * CompletableFuture.runAsync is able to accept a Runnable functional interface and
     * return a CompletableFuture<Void> which doesn't have value
     *
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    @Test
    void letsRunAsync() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture
                .runAsync(() -> print("runAsync has no return values (void)"));
        assertThat(completableFuture.get()).isNull();
    }

    /**
     * You're able to add thenRun() or thenRunAsync() to the runAsync callbacks chain.
     *
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    @Test
    void letsRunAsyncWithCallbacks() throws ExecutionException, InterruptedException {
        CompletableFuture c = CompletableFuture
                .runAsync(() -> print("runAsync"))
                .thenRunAsync(() -> print("thenRunAsync callback example"));

        assertThat(c.get()).isNull();
    }

    @Test
    void letsRunAsyncWithExecutor() throws ExecutionException, InterruptedException {
        CompletableFuture c = CompletableFuture
                .runAsync(() -> print("Run runAsync with an Executor"), executorService);

        assertThat(c.get()).isNull();
    }

    /**
     * CompletableFuture.supplyAsync is able to accept a Supplier<U> functional interface
     * and return a CompletableFuture<U>
     *
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    @Test
    void letsSupplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture c = CompletableFuture
                .supplyAsync(() -> "supplyAsync has return value (CompletableFuture)");

        assertThat(c.get()).isEqualTo("supplyAsync has return value (CompletableFuture)");
    }

    /**
     * You're able to add thenApply(), thenApplyAsync(), thenCombine(), thenCombineAsync(),
     * thenCompose(), thenComposeAsync(), thenAccept(), thenAcceptAsync(), thenRun(), thenRunAsync() to the
     * supplyAsync callbacks chain.
     *
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    @Test
    void letsSupplyAsyncWithCallbacks() throws ExecutionException, InterruptedException {
        CompletableFuture c = CompletableFuture
                .supplyAsync(() -> "supplyAsync")
                .thenApplyAsync((s) -> s + " callback");

        assertThat(c.get()).isEqualTo("supplyAsync callback");
    }

    @Test
    void letsSupplyAsyncWithExecutor() throws ExecutionException, InterruptedException {
        CompletableFuture c = CompletableFuture
                .supplyAsync(() -> "run supplyAsync with an Executor", executorService);
        assertThat(c.get()).isEqualTo("run supplyAsync with an Executor");
    }

    /**
     * Custom print in console.
     *
     * @param message message
     */
    private static void print(String message) {
        String lineBreaker = System.lineSeparator();
        System.out.println(String.join(
                "",
                "::::::::::::::::::::::::::::::::::::::::::::::",
                lineBreaker,
                message,
                lineBreaker,
                "::::::::::::::::::::::::::::::::::::::::::::::"
        ));
    }
}
