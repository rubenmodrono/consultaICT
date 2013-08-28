package com.telefonica.ict.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="T_PROVINCES")
public class Province {

	@Id
	private Integer provinceId;
	private String name;
	@OneToMany(mappedBy="province")
	private Set<ICT> provinceIcts = new HashSet<ICT>();
	
	public Integer getProvinceId() {
		return provinceId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/*package*/Set<ICT> _getProvinceIcts() {
		return provinceIcts;
	}
	
	public Set<ICT> getProvinceIcts(){
		return Collections.unmodifiableSet(provinceIcts);
	}
	
	public void addICT(ICT ict){
		ict.setProvince(this);
		provinceIcts.add(ict);
	}
	
	public void removeICT(ICT ict){
		this.provinceIcts.remove(ict);
		ict.setProvince(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((provinceId == null) ? 0 : provinceId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Province other = (Province) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (provinceId == null) {
			if (other.provinceId != null)
				return false;
		} else if (!provinceId.equals(other.provinceId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Province [provinceId=" + provinceId + ", name=" + name + "]";
	}
	
	

}
