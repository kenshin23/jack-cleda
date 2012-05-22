/*
 * Created on 05/06/2007
 */
package com.minotauro.echo.validator.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.validator.base.BaseValidator;

/**
 * @author Demián Gutierrez
 */
public class ConditionalValidator extends BaseValidator {

  protected enum ConditionalType {
    CHAIN, NOT, AND, OR, IF;
  }

  // --------------------------------------------------------------------------------

  protected List<BaseValidator> baseValidatorList = //
  new ArrayList<BaseValidator>();

  protected ConditionalType conditionalType;

  protected BaseValidator baseValidatorA;
  protected BaseValidator baseValidatorB;

  protected boolean chained;

  // --------------------------------------------------------------------------------

  protected ConditionalValidator(ConditionalType conditionalType, //
      BaseValidator baseValidatorA, BaseValidator baseValidatorB, //
      boolean chained) {
    super(null, null);

    this.conditionalType = conditionalType;

    this.baseValidatorA = baseValidatorA;
    this.baseValidatorB = baseValidatorB;

    this.chained = chained;
  }

  // --------------------------------------------------------------------------------

  public List<String> validateAll(
      String command, ProcessContext processContext) {

    List<String> retList = new ArrayList<String>();

    List<String> retListA = null;
    List<String> retListB = null;

    if (baseValidatorA != null) {
      retListA = baseValidatorA.validateAll(
          command, processContext);
    }

    if (baseValidatorB != null) {
      retListB = baseValidatorB.validateAll(
          command, processContext);
    }

    boolean ret = false;

    switch (conditionalType) {
      case CHAIN :
        ret = true;
        break;
      case NOT :
        ret = /**/!CollectionUtils.isEmpty(retListA);
        break;
      case AND :
        ret = CollectionUtils.isEmpty(retListA) && //
            CollectionUtils.isEmpty(retListB);
        break;
      case OR :
        ret = CollectionUtils.isEmpty(retListA) || //
            CollectionUtils.isEmpty(retListB);
        break;
      case IF :
        ret = /* */CollectionUtils.isEmpty(retListA);
        break;
    }

    if (!ret) {
      return retList;
    }

    for (BaseValidator validator : baseValidatorList) {
      int msgListSize = retList.size();

      List<String> vldList = validator.validateAll(
          command, processContext);

      if (vldList != null) {
        retList.addAll(vldList);
      }

      if (chained & msgListSize != retList.size()) {
        break;
      }
    }

    return retList;
  }

  // --------------------------------------------------------------------------------

  public ConditionalValidator add(BaseValidator validator) {
    baseValidatorList.add(validator);

    return this;
  }

  // --------------------------------------------------------------------------------

  @Override
  public String doValidate(
      Object value, ProcessContext processContext) {

    throw new UnsupportedOperationException();
  }

  // --------------------------------------------------------------------------------

  public static ConditionalValidator CHAIN() {
    return CHAIN(false);
  }

  public static ConditionalValidator CHAIN( //
      boolean chained) {
    return new ConditionalValidator( //
        ConditionalType.CHAIN, null, null, chained);
  }

  // --------------------------------------------------------------------------------

  public static ConditionalValidator NOT( //
      BaseValidator baseValidatorA) {
    return NOT(baseValidatorA, false);
  }

  public static ConditionalValidator NOT( //
      BaseValidator baseValidatorA, boolean chained) {
    return new ConditionalValidator( //
        ConditionalType.NOT, baseValidatorA, null, chained);
  }

  // --------------------------------------------------------------------------------

  public static ConditionalValidator AND( //
      BaseValidator baseValidatorA, BaseValidator baseValidatorB) {
    return AND(baseValidatorA, baseValidatorB, false);
  }

  public static ConditionalValidator AND( //
      BaseValidator baseValidatorA, BaseValidator baseValidatorB, boolean chained) {
    return new ConditionalValidator( //
        ConditionalType.AND, baseValidatorA, baseValidatorB, chained);
  }

  // --------------------------------------------------------------------------------

  public static ConditionalValidator OR( //
      BaseValidator baseValidatorA, BaseValidator baseValidatorB) {
    return OR(baseValidatorA, baseValidatorB, false);
  }

  public static ConditionalValidator OR( //
      BaseValidator baseValidatorA, BaseValidator baseValidatorB, boolean chained) {
    return new ConditionalValidator( //
        ConditionalType.OR, baseValidatorA, baseValidatorB, chained);
  }

  // --------------------------------------------------------------------------------

  public static ConditionalValidator IF( //
      BaseValidator baseValidatorA) {
    return IF(baseValidatorA, false);
  }

  public static ConditionalValidator IF( //
      BaseValidator baseValidatorA, boolean chained) {
    return new ConditionalValidator( //
        ConditionalType.IF, baseValidatorA, null, chained);
  }
}
