package ch.hcuge.simed.gbserver.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.hcuge.simed.gbserver.InitRunner;
import ch.hcuge.simed.initgbserser.TreeNodes;


@RestController
@CrossOrigin(origins = "*")
public class TreeNodeController {
	
    private static Logger logger = LoggerFactory.getLogger(TreeNodeController.class);


	@Autowired
	private InitRunner runner;
	


	@RequestMapping(method = RequestMethod.GET,path = "/services/v2/tree_nodes", produces="application/json")
	public TreeNodes treeNodes(@RequestParam("depth") int depth,@RequestParam("root") String root)
	{
//		TreeNodes treeNodes = new TreeNodes();
//		treeNodes.setTree_nodes(make_ontology());
//		return treeNodes;	
		
		return runner.getTreeNodes(depth, root);
	}
// move to service 
//	private  List<TreeNode> make_ontology() {
//
//
//		final TreeNode root = new TreeNode();
//		root.setName("Test SIMED");
//		root.setStudyId("TEST");
//		root.setFullName("\\Test SIMED\\");
//		root.setType("STUDY");
//		root.setVisualAttributes(new String[] { "FOLDER", "ACTIVE", "STUDY" });
//
//		final Constraint constraint1 = new Constraint();
//		constraint1.setType("study_name");
//		constraint1.setStudyId("TEST");
//		root.setConstraint(constraint1);
//
//		final TreeNode child1 = new TreeNode();
//		child1.setName("01. Patient Information");
//		child1.setFullName("\\Test SIMED\\01. Patient Information\\");
//		child1.setStudyId("TEST");
//		child1.setType("UNKNOWN");
//
//		final Constraint constraint2 = new Constraint();
//
//		final Constraint constraint21 = new Constraint();
//		constraint21.setType("concept");
//
//		final Constraint constraint22 = new Constraint();
//		constraint22.setType("study_name");
//		constraint22.setStudyId("TEST");
//
//		constraint2.setArgs(new Constraint[] { constraint21, constraint22 });
//		constraint2.setType("and");
//
//		child1.setConstraint(constraint2);
//
//		final Metadata metadata_patient = new Metadata();
//		metadata_patient.setSubjectDimension("patient");
//
//		final Metadata metadata_visit = new Metadata();
//		metadata_visit.setSubjectDimension("visit");
//
//		final TreeNode child2 = new TreeNode();
//		child2.setName("Gender");
//		child2.setConceptCode("Patient.gender");
//		child2.setConceptPath("\\Test SIMED\\01. Patient Information\\Gender\\");
//		child2.setFullName("\\Test SIMED\\01. Patient Information\\Gender\\");
//		child2.setStudyId("TEST");
//		child2.setType("CATEGORICAL");
//		child2.setMetadata(metadata_patient);
//
//		final TreeNode child3 = new TreeNode();
//		child3.setName("Age");
//		child3.setConceptCode("Patient.age");
//		child3.setConceptPath("\\Test SIMED\\01. Patient Information\\Age\\");
//		child3.setFullName("\\Test SIMED\\01. Patient Information\\Age\\");
//		child3.setStudyId("TEST");
//		child3.setType("NUMERIC");
//		child3.setMetadata(metadata_patient);
//
//		child1.setChildren(new TreeNode[] { child2, child3 });
//
//
//		final TreeNode child4 = new TreeNode();
//		child4.setName("02. Visit Information");
//		child4.setFullName("\\Test SIMED\\02. Visit Information\\");
//		child4.setStudyId("TEST");
//		child4.setType("UNKNOWN");
//
//		final TreeNode child41 = new TreeNode();
//		child41.setName("Location");
//		child41.setFullName("\\Test SIMED\\02. Visit Information\\Location\\");
//		child41.setStudyId("TEST");
//		child41.setType("UNKNOWN");
//
//		final TreeNode child5 = new TreeNode();
//		child5.setName("Sermed");
//		child5.setConceptCode("Visit.sermed");
//		child5.setConceptPath("\\Test SIMED\\02. Visit Information\\Location\\Sermed\\");
//		child5.setFullName("\\Test SIMED\\02. Visit Information\\Location\\Sermed\\");
//		child5.setStudyId("TEST");
//		child5.setType("CATEGORICAL");
//		child5.setMetadata(metadata_visit);
//
//		child5.setConstraint(constraint2);
//
//		final TreeNode child6 = new TreeNode();
//		child6.setName("Careunit");
//		child6.setConceptCode("Visit.careunit");
//		child6.setConceptPath("\\Test SIMED\\02. Visit Information\\Location\\Careunit\\");
//		child6.setFullName("\\Test SIMED\\02. Visit Information\\Location\\Careunit\\");
//		child6.setStudyId("TEST");
//		child6.setType("CATEGORICAL");
//		child6.setMetadata(metadata_visit);
//
//		child6.setConstraint(constraint2);
//
//		final TreeNode child7 = new TreeNode();
//		child7.setName("Care level");
//		child7.setConceptCode("Visit.carelevel");
//		child7.setConceptPath("\\Test SIMED\\02. Visit Information\\Location\\Care level\\");
//		child7.setFullName("\\Test SIMED\\02. Visit Information\\Location\\Care level\\");
//		child7.setStudyId("TEST");
//		child7.setType("CATEGORICAL");
//		child7.setMetadata(metadata_visit);
//
//		child7.setConstraint(constraint2);
//
//
//		final TreeNode child8 = new TreeNode();
//		child8.setName("Date");
//		child8.setConceptCode("Visit.date");
//		child8.setConceptPath("\\Test SIMED\\02. Patient Location\\Date\\");
//		child8.setFullName("\\Test SIMED\\02. Patient Location\\Date\\");
//		child8.setStudyId("TEST");
//		child8.setType("DATE");
//		child8.setMetadata(metadata_visit);
//
//		child8.setConstraint(constraint2);
//
//
//		final TreeNode child9 = new TreeNode();
//		child9.setName("Period");
//		child9.setConceptCode("Visit.period");
//		child9.setConceptPath("\\Test SIMED\\02. Patient Location\\Period\\");
//		child9.setFullName("\\Test SIMED\\02. Patient Location\\Period\\");
//		child9.setStudyId("TEST");
//		child9.setType("CATEGORICAL");
//		child9.setMetadata(metadata_visit);
//
//		child9.setConstraint(constraint2);
//
//
//		child41.setChildren(new TreeNode[] { child5, child6, child7 });
//		child4.setChildren(new TreeNode[] { child41, child8, child9 });
//
//		root.setChildren(new TreeNode[] { child1, child4 });
//		return Arrays.asList(root);
//	}


}
