package de.emeraldmc.playerinfo.listener;

import de.emeraldmc.playerinfo.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://geoip.nekudo.com/api/"+e.getPlayer().getAddress().toString().replaceAll("/","").split(":")[0]);
                    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                    String inLine;
                    String lineSum = new String();
                    while ((inLine = in.readLine()) != null) {
                        lineSum += inLine;
                    }
                    in.close();
                    if (lineSum.contains("error")) {
                        Main.getInstance().playerCountryMap.put(e.getPlayer(), "Unavailable");
                    }
                    String[] arr = lineSum.split(",");
                    String c = arr[1];
                    c = c.replaceAll(Pattern.quote("{"), "");
                    c = c.replaceAll(String.valueOf('"'), "");
                    c = c.replaceAll("country:name:", "");

                    Main.getInstance().playerCountryMap.put(e.getPlayer(), c);
                    return;
                } catch (MalformedURLException ex) {
                } catch (IOException ex) {
                }
                Main.getInstance().playerCountryMap.put(e.getPlayer(), "Unavailable");
            }
        }, 5l);

    }
}
