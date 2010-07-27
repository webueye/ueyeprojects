package net.ueye.module.custom;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.Mapping;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.type.Type;

public class UeyeAndFunction implements SQLFunction {

	public Type getReturnType(Type type, Mapping mapping) throws QueryException {
		// TODO Auto-generated method stub
		return Hibernate.INTEGER;
	}

	public boolean hasArguments() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean hasParenthesesIfNoArguments() {
		// TODO Auto-generated method stub
		return true;
	}

	@SuppressWarnings("unchecked")
	public String render(List args, SessionFactoryImplementor sf)
			throws QueryException {
		// TODO Auto-generated method stub
		if(args.size()!=2){
			throw new IllegalArgumentException("参数不匹配 。。。");
		}
		return args.get(0).toString()+"&"+args.get(1);
	}

}
