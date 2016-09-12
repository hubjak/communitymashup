

package org.sociotech.mashupsync.api;

import java.util.List;

import javax.xml.bind.annotation.*;


@XmlRootElement(name="DataSet", namespace = "http://data.cscm.communitymashup.de")

public class MashupGenericIndex {
	@XmlElement(name="items") 
	public List<MashupItem> any;
}
