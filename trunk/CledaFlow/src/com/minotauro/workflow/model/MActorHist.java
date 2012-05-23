/*
 * Created on 17/01/2008
 */
package com.minotauro.workflow.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_actor_hist")
@PrimaryKeyJoinColumn(name = "actor_id")
@Inheritance(strategy = InheritanceType.JOINED)
@Proxy(lazy = false)
public class MActorHist extends MActor {
  // Dummy
}
