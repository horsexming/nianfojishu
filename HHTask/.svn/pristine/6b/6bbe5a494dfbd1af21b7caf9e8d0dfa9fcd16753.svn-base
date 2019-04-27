package com.task.Server.codetranslation;

import java.io.File;
import java.util.Map;

import com.task.entity.claim.CustomerClaim;
import com.task.entity.codetranslation.CodeTranslation;

public interface CodeTranslationServer {
	
	public CodeTranslation findById(Integer id);
	
	public boolean save(CodeTranslation codeTranslation);
	
	public boolean delete(CodeTranslation codeTranslation);
	
	public boolean update(CodeTranslation codeTranslation);
	
	public Map<Integer, Object> findAll(CodeTranslation codeTranslation, int pageNo, int pageSize,String tag);
	
	public String importFile(File importFile);
	
	public String exportFile(File exportFile);
	
	public boolean findSame(CodeTranslation codeTranslation);
	
	public CodeTranslation findByKeyCode(String keyCode);
	
	public Map<Integer, Object> QueryCode(CodeTranslation codeTranslation, int pageNo, int pageSize,String tag);
	
}
