package  com.radium.client.events.event;

public abstract class CancellableEvent implements Event {
   private boolean cancelled = false;

   public boolean isCancelled() {
      return this.cancelled;
   }

   public void cancel() {
      this.cancelled = true;
   }
