package me.n1ar4.rasp.agent.ent;

import java.util.List;

public class Config {
    private String version;
    private boolean debug;
    private boolean block;

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    private List<HookInfo> hooks;

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<HookInfo> getHooks() {
        return hooks;
    }

    public void setHooks(List<HookInfo> hooks) {
        this.hooks = hooks;
    }
}
