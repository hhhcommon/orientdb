/* Generated By:JJTree: Do not edit this line. OMatchesCondition.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.orientechnologies.orient.core.sql.parser;

import com.orientechnologies.orient.core.command.OCommandContext;
import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.sql.executor.OResult;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OMatchesCondition extends OBooleanExpression {
  protected OExpression     expression;
  protected String          right;
  protected OInputParameter rightParam;

  public OMatchesCondition(int id) {
    super(id);
  }

  public OMatchesCondition(OrientSql p, int id) {
    super(p, id);
  }

  /**
   * Accept the visitor.
   **/
  public Object jjtAccept(OrientSqlVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  @Override
  public boolean evaluate(OIdentifiable currentRecord, OCommandContext ctx) {
    String regex = right;
    if (regex != null) {
      regex = regex.substring(1, regex.length() - 1);
    } else {
      Object paramVal = rightParam.getValue(ctx.getInputParameters());
      if (paramVal instanceof String) {
        regex = (String) paramVal;
      } else {
        return false;
      }
    }
    Object value = expression.execute(currentRecord, ctx);

    return matches(value, regex, ctx);
  }

  private boolean matches(Object value, String regex, OCommandContext ctx) {
    final String key = "MATCHES_" + regex.hashCode();
    java.util.regex.Pattern p = (java.util.regex.Pattern) ctx.getVariable(key);
    if (p == null) {
      p = java.util.regex.Pattern.compile(regex);
      ctx.setVariable(key, p);
    }

    if (value instanceof CharSequence) {
      return p.matcher((CharSequence) value).matches();
    } else {
      return false;
    }
  }

  @Override
  public boolean evaluate(OResult currentRecord, OCommandContext ctx) {
    String regex = right;
    if (regex != null) {
      regex = regex.substring(1, regex.length() - 1);
    } else {
      Object paramVal = rightParam.getValue(ctx.getInputParameters());
      if (paramVal instanceof String) {
        regex = (String) paramVal;
      } else {
        return false;
      }
    }
    Object value = expression.execute(currentRecord, ctx);

    return matches(value, regex, ctx);
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    expression.toString(params, builder);
    builder.append(" MATCHES ");
    if (right != null) {
      builder.append(right);
    } else {
      rightParam.toString(params, builder);
    }
  }

  @Override
  public boolean supportsBasicCalculation() {
    return expression.supportsBasicCalculation();
  }

  @Override
  protected int getNumberOfExternalCalculations() {
    if (expression != null && !expression.supportsBasicCalculation()) {
      return 1;
    }
    return 0;
  }

  @Override
  protected List<Object> getExternalCalculationConditions() {
    if (expression != null && !expression.supportsBasicCalculation()) {
      return (List) Collections.singletonList(expression);
    }
    return Collections.EMPTY_LIST;
  }

  @Override
  public boolean needsAliases(Set<String> aliases) {
    if (expression.needsAliases(aliases)) {
      return true;
    }
    return false;
  }

  @Override
  public OMatchesCondition copy() {
    OMatchesCondition result = new OMatchesCondition(-1);
    result.expression = expression == null ? null : expression.copy();
    result.right = right;
    result.rightParam = rightParam == null ? null : rightParam.copy();
    return result;
  }

  @Override
  public void extractSubQueries(SubQueryCollector collector) {
    expression.extractSubQueries(collector);
  }

  @Override
  public boolean refersToParent() {
    if (expression != null && expression.refersToParent()) {
      return true;
    }
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    OMatchesCondition that = (OMatchesCondition) o;

    if (expression != null ? !expression.equals(that.expression) : that.expression != null)
      return false;
    if (right != null ? !right.equals(that.right) : that.right != null)
      return false;
    if (rightParam != null ? !rightParam.equals(that.rightParam) : that.rightParam != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = expression != null ? expression.hashCode() : 0;
    result = 31 * result + (right != null ? right.hashCode() : 0);
    result = 31 * result + (rightParam != null ? rightParam.hashCode() : 0);
    return result;
  }

  @Override
  public List<String> getMatchPatternInvolvedAliases() {
    return expression.getMatchPatternInvolvedAliases();
  }

  @Override
  public boolean isCacheable() {
    return expression.isCacheable();
  }

}
/* JavaCC - OriginalChecksum=68712f476e2e633c2bbfc34cb6c39356 (do not edit this line) */
