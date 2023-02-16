package de.htx1.multiaccount.handlers;

import de.htx1.multiaccount.utils.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created: 16.02.2023 00:19
 *
 * @author thvf
 */
public class AccountHandler {

    Config config = new Config("plugins/multiaccount/", "stored.yml");
    String ipv4;

    public AccountHandler(final String ipv4) {
        this.ipv4 = ipv4;
        if(config.getConfig().get(ipv4 + ".Accounts") == null) config.getConfig().set(ipv4 + ".Accounts", new ArrayList<>());
    }


    public List<String> getAccountsByIP() {
        return this.config.getConfig().getStringList(ipv4 + ".Accounts");
    }


    public void manipulateList(final UUID uuid, final boolean manipulate) {

        final List<String> tempList = this.config.getConfig().getStringList(ipv4 + ".Accounts");

        if(!manipulate) {
            tempList.remove(uuid.toString());
        } else {
            tempList.add(uuid.toString());
        }
        this.config.getConfig().set(ipv4 + ".Accounts", tempList);
        this.config.saveConfig();


    }


}
