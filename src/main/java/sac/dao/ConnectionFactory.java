/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.dao;

import java.sql.*;

/**
 *
 * @author geova
 */

public final class ConnectionFactory implements AutoCloseable {
//postgres://ouytnspi:jzcpmqe4b55hmZBNkA0U_VEfKpzBArGd@batyr.db.elephantsql.com/ouytnspi
//    private static final String DRIVER = "org.postgresql.Driver";
//    private static final String URL = "jdbc:postgresql://localhost:5432/db_sac";
//    private static final String LOGIN = "sac_user";
//    private static final String SENHA = "sac_123";

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://batyr.db.elephantsql.com/ouytnspi";
    private static final String LOGIN = "ouytnspi";
    private static final String SENHA = "jzcpmqe4b55hmZBNkA0U_VEfKpzBArGd";
    private static Connection con = null;

    public static Connection getConnection() throws DAOException {
        if (con == null) {
            try {
                Class.forName(DRIVER);
                con = DriverManager.getConnection(URL, LOGIN, SENHA);
            } catch (ClassNotFoundException e) {
                throw new DAOException("Driver do banco não encontrado: "
                        + DRIVER, e);

            } catch (SQLException e) {
                throw new DAOException("Erro conectando ao BD: " + URL + "/"
                        + LOGIN + "/" + SENHA, e);

            }
        }
        return con;
    }

    @Override
    public void close() throws DAOException {
        if(con!=null){
            try{
                con.close();
                con=null;
            }catch(Exception e){
                System.out.println("Erro fechando conexão");
                e.printStackTrace();
            }
        }
    }
}
