package eu.fiestaiot.portal.testbed.service.dto;

import javax.validation.constraints.NotNull;

public class GetTestbedByIriAndNameDTO {
	@NotNull
	private String iri;
	@NotNull
	private String name;

	public String getIri() {
		return iri;
	}

	public void setIri(String iri) {
		this.iri = iri;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
