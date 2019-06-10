package ch.heigvd.mcr.bridgehack.player.roles;

public abstract class Role {
    final private String BASE_IMG_NAME;

    /**
     * Role constructor
     *
     * @param baseImgName The base image name for this role
     */
    protected Role(String baseImgName) {
        BASE_IMG_NAME = baseImgName;
    }

    /**
     * Simple getter for the base image name
     *
     * @return The base image name for this role
     */
    public String getBaseImageNameImpl() {
        return BASE_IMG_NAME;
    }
}
