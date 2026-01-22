import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.World;

public class ChunkDataEvent {
    private final Chunk chunk;

    public ChunkDataEvent(Chunk chunk) {
        this.chunk = chunk;
    }

    public Chunk getChunk() {
        return chunk;
    }
}
