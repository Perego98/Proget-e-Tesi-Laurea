package com.tesi.gestione.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.sql.rowset.serial.SerialException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import com.tesi.gestione.crm.CrmCandidato;
import com.tesi.gestione.crm.CrmCandidatoUpdate;
import com.tesi.gestione.crm.CrmStato;
import com.tesi.gestione.dao.CandidatoDao;
import com.tesi.gestione.dao.SedeDao;
import com.tesi.gestione.dao.UserDao;
import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.entity.User;

@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	private CandidatoDao candidatoDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SedeDao sedeDao;

	@Override
	@Transactional
	public List<Candidato> getCandidatiAssociati(String userUsername) {
		return candidatoDao.getCandidatiAssociati(userUsername);
	}
	
	

}
