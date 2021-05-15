package com.htttql.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
public class SampleReport {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private boolean status;
	
	@Lob
	private String expression;
	private String content;
	
	@OneToMany(mappedBy = "type")
	private List<Report> listReports;
}
