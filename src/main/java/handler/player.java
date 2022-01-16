package handler;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import core.network;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Objects;

import static main.main.PermittedViolationLevel;
import static main.main.PlayerBanTime;
import static main.whes1015.DATA;

public class player {

    public static void check() {
        JsonElement Data = JsonParser.parseString(DATA.toString());
        JsonObject data = Data.getAsJsonObject();
        data.addProperty("Type", "PlayerCheck");
        data.addProperty("FormatVersion", 1);
        JsonArray playerlist = new JsonArray();
        for (Player player : Bukkit.getOnlinePlayers()) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", player.getName());
            jsonObject.addProperty("uuid", String.valueOf(player.getUniqueId()));
            playerlist.add(jsonObject);
        }
        data.add("PlayerList", playerlist);
        JsonObject PostData = network.Post(data);
        JsonObject jsonObject = PostData.get("response").getAsJsonObject();
        if (Objects.equals(jsonObject.get("response").getAsString(), "null")) return;
        if (jsonObject.get("degree").getAsInt() > PermittedViolationLevel) {
            if (PlayerBanTime == 0) {
                Objects.requireNonNull(Bukkit.getPlayer(PostData.get("addition").getAsJsonObject().get("name").getAsString())).kickPlayer(MessageFormat.format(ChatColor.AQUA + "\u96f2\u7aef\u9632\u8b77\u7cfb\u7d71\n" + ChatColor.RED + "\u5c01\u7981\u539f\u56e0 >> {0}\n" + ChatColor.GOLD + "\u5c01\u7981\u6642\u9593 >> \u6c38\u4e45", decode.Decode(jsonObject.get("reason").getAsInt())));
            } else if (jsonObject.get("time").getAsLong() + PlayerBanTime * 1000 > System.currentTimeMillis()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(jsonObject.get("time").getAsLong() + PlayerBanTime * 1000);
                Objects.requireNonNull(Bukkit.getPlayer(PostData.get("addition").getAsJsonObject().get("name").getAsString())).kickPlayer(MessageFormat.format(ChatColor.AQUA + "\u96f2\u7aef\u9632\u8b77\u7cfb\u7d71\n" + ChatColor.RED + "\u5c01\u7981\u539f\u56e0 >> {0}\n" + ChatColor.GOLD + "\u5c01\u7981\u6642\u9593 >> {1} \u5e74 {2} \u6708 {3} \u65e5 - {4} \u6642 {5} \u5206 {6} \u79d2", decode.Decode(jsonObject.get("reason").getAsInt()), calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND)));
            }
        }
    }
}
