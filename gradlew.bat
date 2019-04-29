package com.bumptech.glide.request;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * A {@link java.util.concurrent.Future} implementation for Glide that can be used to load resources
 * in a blocking manner on background threads.
 *
 * <p> Note - Unlike most targets, RequestFutureTargets can be used once and only once. Attempting
 * to reuse a RequestFutureTarget will probably result in undesirable behavior or exceptions.
 * Instead of reusing objects of this class, the pattern should be:
 *
 * <pre>
 *     {@code
 *      FutureTarget<File> target = null;
 *      RequestManager requestManager = Glide.with(context);
 *      try {
 *        target = requestManager
 *           .downloadOnly()
 *           .load(model)
 *           .submit();
 *        File downloadedFile = target.get();
 *        // ... do something with the file (usually throws IOException)
 *      } catch (ExecutionException | InterruptedException | IOException e) {
 *        // ... bug reporting or recovery
 *      } finally {
 *        // make sure to cancel pending operations and free resources
 *        if (target != null) {
 *          target.cancel(true); // mayInterruptIfRunning
 *        }
 *      }
 *     }
 *     </pre>
 * The {@link #cancel(boolean)} call will cancel pending operations and
 * make sure that any resources used are recycled.
 * </p>
 *
 * @param <R> The type of the resource that will be loaded.
 */
public class RequestFutureTarget<R> implements FutureTarget<R>,
    RequestListener<R>,
    Runnable {
  private static final Waiter DEFAULT_WAITER = new Waiter();

  private final Handler main