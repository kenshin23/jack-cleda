/*
 * Created on 25/06/2007
 */
package com.minotauro.user.model;

import java.text.MessageFormat;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;

import com.minotauro.base.model.MBase;

/**
 * @author Demián Gutierrez
 */
public class UserUtil {

  private UserUtil() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public static MProf findProf(Session session, String name) {
    return MBase.loadByField(session, MProf.class, _PropMProf.NAME, name);
  }

  public static MPriv findPriv(Session session, String name) {
    return MBase.loadByField(session, MPriv.class, _PropMPriv.NAME, name);
  }

  public static MRole findRole(Session session, String name) {
    return MBase.loadByField(session, MRole.class, _PropMRole.NAME, name);
  }

  // --------------------------------------------------------------------------------

  public static void addProfToUser(Session session, MUser user, String name) {
    MProf prof = findProf(session, name);

    if (prof == null) {
      throw new NullPointerException(MRole.class.getName() + ": " + name);
    }

    MUserProf userProf = new MUserProf();

    user.getUserProfList().add(userProf);
    userProf.setUserRef(user);

    prof.getUserProfList().add(userProf);
    userProf.setProfRef(prof);
  }

  // --------------------------------------------------------------------------------

  public static void addPrivToProf(Session session, MProf prof, String name) {
    MPriv priv = findPriv(session, name);

    if (priv == null) {
      throw new NullPointerException(MPriv.class.getName() + ": " + name);
    }

    MProfPriv profPriv = new MProfPriv();

    prof.getProfPrivList().add(profPriv);
    profPriv.setProfRef(prof);

    priv.getProfPrivList().add(profPriv);
    profPriv.setPrivRef(priv);
  }

  // --------------------------------------------------------------------------------

  public static void addRoleToProf(Session session, MProf prof, String name) {
    MRole role = findRole(session, name);

    if (role == null) {
      throw new NullPointerException(MRole.class.getName() + ": " + name);
    }

    MProfRole profRole = new MProfRole();

    prof.getProfRoleList().add(profRole);
    profRole.setProfRef(prof);

    role.getProfRoleList().add(profRole);
    profRole.setRoleRef(role);
  }

  // --------------------------------------------------------------------------------

  public static MProf createProf(Session session, String name, String description) {
    MProf prof = new MProf();

    prof.setName(name);
    prof.setDescription(description);

    session.saveOrUpdate(prof);

    return prof;
  }

  // --------------------------------------------------------------------------------

  public static MPriv createPriv(Session session, String name, String description) {
    MPriv priv = new MPriv();

    priv.setName(name);
    priv.setDescription(description);

    session.saveOrUpdate(priv);

    return priv;
  }

  // --------------------------------------------------------------------------------

  public static MRole createRole(Session session, String name, String description) {
    MRole role = new MRole();

    role.setName(name);
    role.setDescription(description);

    session.saveOrUpdate(role);

    return role;
  }

  // --------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public static Iterator<MUser> iterateUserWithPriv(Session session, String privName) {
    String hql = "SELECT DISTINCT  user " + //
        "FROM MUser             AS user " + //
        "JOIN user.userProfList AS uspr " + //
        "JOIN uspr.profRef      AS prof " + //
        "JOIN prof.profPrivList AS prpr " + //
        "JOIN prpr.privRef      AS priv " + //
        "WHERE priv.name=:privName";

    hql = MessageFormat.format(hql, new Object[]{MUser.class.getName()});

    Query query = session.createQuery(hql);
    query.setParameter("privName", privName);

    return query.iterate();
  }

  // --------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public static Iterator<MUser> iterateUserWithRole(Session session, String roleName) {
    String hql = "SELECT DISTINCT  user " + //
        "FROM MUser             AS user " + //
        "JOIN user.userProfList AS uspr " + //
        "JOIN uspr.profRef      AS prof " + //
        "JOIN prof.profRoleList AS prro " + //
        "JOIN prro.roleRef      AS role " + //
        "WHERE role.name=:roleName";

    hql = MessageFormat.format(hql, new Object[]{MUser.class.getName()});

    Query query = session.createQuery(hql);
    query.setParameter("roleName", roleName);

    return query.iterate();
  }
}
