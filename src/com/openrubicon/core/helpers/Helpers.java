package com.openrubicon.core.helpers;

import com.rits.cloning.Cloner;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Helpers {

    public static Random rng = new Random();

    public static Cloner cloner = new Cloner();

    public static void seedRng(long seed)
    {
        rng.setSeed(seed);
    }

    public static ArrayList<Player> getNearbyPlayers(Player pl, double range){
        ArrayList<Player> nearby = new ArrayList<Player>();
        for (Entity e : pl.getNearbyEntities(range, range, range)){
            if (e instanceof Player){
                nearby.add((Player) e);
            }
        }
        return nearby;
    }

    public static ArrayList<Entity> getNearbyEntities(Player pl, double range){
        ArrayList<Entity> nearby = new ArrayList<Entity>();
        for (Entity e : pl.getNearbyEntities(range, range, range)){
            nearby.add(e);
        }
        return nearby;
    }

    public static String colorize(String message)
    {
        return ChatColor.translateAlternateColorCodes('&',message);
    }

    public static ArrayList<String> colorize(ArrayList<String> messages)
    {
        ArrayList<String> coloredMessages = new ArrayList<>();
        for(String s : messages)
        {
            coloredMessages.add(Helpers.colorize(s));
        }
        return coloredMessages;
    }

    public static double round(double value, int places)
    {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static int roundToInt(double value)
    {
        return (int)Helpers.round(value, 0);
    }

    public static boolean isNumeric(String str)
    {
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

    public static double randomDouble(double min, double max)
    {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static float randomFloat(float min, float max)
    {
        return (ThreadLocalRandom.current().nextFloat() * (max - min)) + min;
    }

    public static int randomInt(int min, int max)
    {
        return (Helpers.rng.nextInt(max - min)) + min;
    }

    public static double scale(final double valueIn, final double baseMin, final double baseMax, final double limitMin, final double limitMax) {
        return ((limitMax - limitMin) * (valueIn - baseMin) / (baseMax - baseMin)) + limitMin;
    }

    public static String camelCaseToReadable(String camelCase)
    {
        return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(camelCase), ' ');
    }

    public static int secondsToTicks(int seconds)
    {
        return seconds * 20;
    }

    public static String[] toArray(ArrayList<String> strings)
    {
        return strings.toArray(new String[0]);
    }

    public static double getDistance(Location location1, Location location2)
    {
        double x = location2.getX() - location1.getX();
        double y = location2.getY() - location1.getY();
        double z = location2.getZ() - location1.getZ();
        double total = Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2);
        return Math.sqrt(total);
    }
}
