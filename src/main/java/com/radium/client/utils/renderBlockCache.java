public static void renderBlockCache(class_4587 matrices,
                                    ConcurrentHashMap<Long, Set<class_2338>> cache,
                                    Color color) {
    MinecraftClient mc = MinecraftClient.getInstance();
    if (mc.player == null) return;

    double playerX = mc.player.getX();
    double playerY = mc.player.getEyeY(); // pentru tracers
    double playerZ = mc.player.getZ();

    for (Set<class_2338> blockSet : cache.values()) {
        if (blockSet == null) continue;

        for (class_2338 blockPos : blockSet) {
            if (blockPos == null) continue;

            // Calcul coordonate relative la jucător
            float x1 = (float) (blockPos.method_10263() - playerX + 0.1);
            float y1 = (float) (blockPos.method_10264() - playerY + 0.05);
            float z1 = (float) (blockPos.method_10260() - playerZ + 0.1);

            float x2 = (float) (blockPos.method_10263() - playerX + 0.9);
            float y2 = (float) (blockPos.method_10264() - playerY + 0.85);
            float z2 = (float) (blockPos.method_10260() - playerZ + 0.9);

            // Render cub ESP
            renderFilledBox(matrices, x1, y1, z1, x2, y2, z2, color);

            // Render tracers dacă e activat
            if (SuspiciousEsp.getInstance().tracers.getValue()) {
                renderLine(matrices, new Color(color.getRed(), color.getGreen(), color.getBlue(), 255),
                        new class_243(playerX, playerY, playerZ),
                        new class_243(blockPos.method_10263() + 0.5,
                                      blockPos.method_10264() + 0.5,
                                      blockPos.method_10260() + 0.5));
            }
        }
    }
}
