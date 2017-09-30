package com.openrubicon.core.database.models;

import com.openrubicon.core.api.database.Connection;
import com.openrubicon.core.api.database.DatabaseModel;
import com.openrubicon.core.api.database.interfaces.DatabaseMigration;
import com.openrubicon.core.database.migrations.CreatePlayers;

import java.util.*;

public class Player extends DatabaseModel<Player> {

    private long id;
    private String uuid = "";
    private String username = "";
    private String display_name = "";
    private String email = "";
    private String password = "";
    private long discord_id;
    private int verified;
    private String token;
    private Date last_joined;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    private String tableName = "rubicon_players";
    private int version = 1;

    public Player() {
    }

    public Player(Connection connection) {
        super(connection);
    }

    public Player(Connection connection, String uuid) {
        super(connection);
        this.uuid = uuid;
    }

    public Player(Connection connection, String uuid, String username, String display_name) {
        super(connection);
        this.uuid = uuid;
        this.username = username;
        this.display_name = display_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getDiscord_id() {
        return discord_id;
    }

    public void setDiscord_id(long discord_id) {
        this.discord_id = discord_id;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLast_joined() {
        return last_joined;
    }

    public void setLast_joined(Date last_joined) {
        this.last_joined = last_joined;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public List<Player> getAll()
    {
        return this.getConnection().get().createQuery("SELECT id,uuid,username,display_name,email,discord_id,token,last_joined,created_at,updated_at,deleted_at FROM rubicon_players WHERE deleted_at is null").executeAndFetch(Player.class);
    }

    public void insertPlayer()
    {
        String sql = "INSERT INTO rubicon_players (uuid, username, email, password display_name) VALUES (:uuid, :username, :email, :password, :display_name)";

        this.getConnection().get().createQuery(sql, true).bind(this).executeUpdate().getKey();
    }

    public Player getPlayer()
    {
        List<Player> p = this.getConnection().get().createQuery("SELECT id,uuid,username,display_name,email,discord_id,token,last_joined,created_at,updated_at,deleted_at FROM rubicon_players WHERE deleted_at is null AND uuid = :uuid").bind(this).executeAndFetch(Player.class);
        return p.get(0);
    }

    public boolean exists()
    {
        return this.getConnection().get().createQuery("SELECT count(id) FROM rubicon_players WHERE deleted_at is null AND uuid = :uuid").bind(this).executeScalar(Integer.class) >= 1;
    }

    public void updatePlayer()
    {
        String sql = "UPDATE rubicon_players SET display_name = :display_name, username = :username, last_joined = :last_joined, token = :token, discord_id = :discord_id, verified = :verified WHERE id = :id";

        this.getConnection().get().createQuery(sql).bind(this).executeUpdate();
    }

    @Override
    public HashMap<Integer, DatabaseMigration> getMigrations() {
        HashMap<Integer, DatabaseMigration> migrations = new HashMap<>();
        migrations.put(1, new CreatePlayers());
        return migrations;
    }

    @Override
    public String getTableName() {
        return this.tableName;
    }

    @Override
    public int getVersion() {
        return this.version;
    }
}
