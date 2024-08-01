package com.learning.springdata.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

// this class will have methods that execute various queries to manipulate rows of the Player table.
@Service
public class PlayerDao {

    // first method returns all rows from the Player table - before that autowire JDBCTempplate
    @Autowired
    JdbcTemplate jdbcTemplate;

    // 1. querying for all objects
    // a rowMapper is used to match the data coming from the database to the attributes of the bean
    // (BeanPropertyRowMapper is the default row mapper defined by Spring)
    public List<Player> getAllPlayers() {
        String sql = "Select * from player";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Player>(Player.class));
    }
    // 2. querying for a specific object based on the Id
    public Player getPlayerById(int id) {
        String sql = "SELECT * FROM PLAYER WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Player>(Player.class), new Object[] {id});
    }
    // 3. insert a record in the database using jdbcTemplate class
    public int insertPlayer(Player player) {
        String sql = "Insert Into Player (Id, Name, Nationality, BirthDate, Titles)" + "Values (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[] {player.getId(), player.getName(), player.getNationality(), new Timestamp(player.getBirthDate().getTime()), player.getTitles()});
    }
    // 4. update an existing record in the database
    public int updatePlayer(Player player) {
        String sql = "Update Player" + "Set Name = ?, Nationality = ?, BirthDate = ?, Titles = ?" + "WHERE ID = ?";
        // note: we have changed the order of getter methods to match the order in which values will be passed to the query
        return jdbcTemplate.update(sql, new Object[] {player.getName(), player.getNationality(), new Timestamp(player.getBirthDate().getTime()), player.getTitles(), player.getId()});
    }
    // 5. use update method to delete a record from the database
    public int deletePlayerById(int id) {
        String sql = "Delete from Player where ID = ?";
        return jdbcTemplate.update(sql, new Object[] {id});
    }

    //  In rare scenerios, we might want to create a table which is a DDL.
    // In that case, we can use the execute() method of the JdbcTemplate.
    public void createTournamentTable() {
        String sql = "Create Table tournament (id integer, name varchar(50), location varchar(50), primary key(id)) ";
        jdbcTemplate.execute(sql);
        System.out.println("Table created");
    }

    // Implementing custom row mapper (if database has a different structure or column names, we need to define our custom mapping)
    // define our row mapper by implementing the RowMapper interface and providing the bean onto which the rows will be mapped.
    // The custom row mapper, PlayerMapper is created as an inner class because it will be only used inside JdbcPlayerDao.
    // (best practice to make it static and final)
    // ..
    private static final class PlayerMapper implements RowMapper<Player> {
        // the RowMapper interface has one method, mapRow, for which we will write our custom implementation to initialize a Player object.
        // this method defines how a row is mapped. (takes two arguments, first being result set and second being the row number)
        @Override
        public Player mapRow (ResultSet resultSet, int rowNumber) throws SQLException {
            Player player = new Player();
            player.setId(resultSet.getInt("id"));
            player.setName(resultSet.getString("name"));
            player.setNationality(resultSet.getString("nationality"));
//            player.setBirthDate(resultSet.getString("birth Date"));
            player.setTitles(resultSet.getInt("titles"));
            return player;
        }
    }
    // Using custom row mapper
    public List<Player> getPlayerByNationality(String nationality) {
        String sql = "Select * From Player where nationality = ?";
        return jdbcTemplate.query(sql, new PlayerMapper(), new Object[] {nationality});
    }
}
