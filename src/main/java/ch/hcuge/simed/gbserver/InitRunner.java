package ch.hcuge.simed.gbserver;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SerializationUtils;

import ch.hcuge.simed.initgbserser.Constraint;
import ch.hcuge.simed.initgbserser.Metadata;
import ch.hcuge.simed.initgbserser.TreeNode;
import ch.hcuge.simed.initgbserser.TreeNodes;
import test.cayenne.OMOP.Concept;
import test.cayenne.OMOP.ConceptAncestor;
import test.cayenne.OMOP.Observation;



@Component
@Service
public class InitRunner implements CommandLineRunner {
	
	final static private String ROOT_NAME = "\\"; 
	
	@Value("${ch.hcuge.simed.gbserver.concept.initfilepath}")
	private String initFilePath;
	
	@Value("${ch.hcuge.simed.gbserver.concept.snomedRootCode}")
	private String snomedRootCode;
	
	@Value("${ch.hcuge.simed.gbserver.concept.snomedRootDescendantCodes}")
	private String snomedRootDescendantCodes;
	
	private ObjectContext newObjectContext = null;
	
    private static Logger logger = LoggerFactory.getLogger(InitRunner.class);


	private List<TreeNode> fullTreeNodes;

//	private TreeNode rootTreeNode;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	private int testNbNodes = 0;

	@Override
	public void run(String... args) throws Exception {
		// test tomcat
//		Files.write( Paths.get("test.txt"), new byte[]{1}, StandardOpenOption.CREATE_NEW);
//		Resource resource = resourceLoader.getResource("classpath:" + initFilePath);// work with resources in IDE. Should work in Tomcat (app/WEB-/classes) ?
//		File file = resource.getFile();
//		Path path = file.toPath();
//		File file = ResourceUtils.getFile(initFilePath);
//		byte[] datatest = Files.readAllBytes(file.toPath());
//		fullTreeNodes = (List<TreeNode>) SerializationUtils.deserialize(datatest);
		
        logger.info("Will try to set ontology cache with file : " + initFilePath);
 //       Path path = Paths.get(initFilePath);// work
		// test with classpath
//		Resource resource = resourceLoader.getResource("classpath:" + initFilePath);// work with resources in IDE. Should work in Tomcat (app/WEB-/classes) ?
//		File file = resource.getFile();
//		Path path = file.toPath();
		// test
//        Path path = null;
		try {
			initFullTreeNodes();
//			byte[] data = Files.readAllBytes(path);
//			fullTreeNodes = (List<TreeNode>) SerializationUtils.deserialize(data);
//			TreeNode rootNode = fullTreeNodes.get(0);
//			printTrees(rootNode);
//			System.err.println("Nb de nodes : " + testNbNodes);
			logger.info("Ontology cache read from file");
		} catch (NoSuchFileException e) {
			logger.error(e.getMessage());
		}
		catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		}
		
        
//        if (fullTreeNodes == null) // deser did not work
//        {
//            logger.info("Ontology cache no set. Will use DB" );
//
////        fullTreeNodes = make_ontology();
//        	fullTreeNodes = makeOntologyFromOMOP();
//            logger.info("Ontology cache set from DB" );
//    		byte[] rootByte = SerializationUtils.serialize(fullTreeNodes);
//    		if (path == null)
//    			path = Paths.get(initFilePath);
//    		Files.write(path, rootByte, StandardOpenOption.CREATE_NEW);
//            logger.info("Ontology cache writed to file" );
//
//        }
//        System.setErr(new PrintStream(new BufferedOutputStream(new FileOutputStream("output.txt"))));
       
// test        
//        TreeNodes testNodes = getTreeNodes (20,"\\Test SIMED\\02. Visit Information\\");
//        logger.info("Test : " + testNodes); 
	}
	


	private TreeNode createDemographics() {
		
		final Metadata metadata_patient = new Metadata();
		metadata_patient.setSubjectDimension("patient");

		final Metadata metadata_visit = new Metadata();
		metadata_visit.setSubjectDimension("visit");
		
	    TreeNode demographics = new TreeNode();
	    demographics.setName("Demographics");// SNOMED CT
	    demographics.setStudyId("TEST");
	    demographics.setFullName("\\" + "Demographics" + "\\");
	    demographics.setType("UNKNOWN");
	    demographics.setVisualAttributes(new String[] { "FOLDER", "ACTIVE" });	
		Constraint constraint1 = new Constraint();
		constraint1.setType("study_name");
		constraint1.setStudyId("TEST");
		demographics.setConstraint(constraint1);
//	    demographics.getChildren().add(addTestChild());
	    
	    
		final TreeNode child1 = new TreeNode();
		child1.setName("01. Patient Information");
		child1.setFullName("\\Demographics\\01. Patient Information\\");
		child1.setStudyId("TEST");
		child1.setType("UNKNOWN");
		child1.setVisualAttributes(new String[] { "FOLDER", "ACTIVE", "STUDY" });

		demographics.getChildren().add(child1);
		
		
		final TreeNode child2 = new TreeNode();
		child2.setName("Gender");
		child2.setConceptCode("Patient.gender");
		child2.setConceptPath("\\Demographics\\01. Patient Information\\Gender\\");
		child2.setFullName("\\Demographics\\01. Patient Information\\Gender\\");
		child2.setStudyId("TEST");
		child2.setType("CATEGORICAL");
		child2.setVisualAttributes(new String[] { "LEAF", "ACTIVE", "CATEGORICAL" });
		child2.setMetadata(metadata_patient);

		final TreeNode child3 = new TreeNode();
		child3.setName("Age");
		child3.setConceptCode("Patient.age");
		child3.setConceptPath("\\Demographics\\01. Patient Information\\Age\\");
		child3.setFullName("\\Demographics\\01. Patient Information\\Age\\");
		child3.setStudyId("TEST");
		child3.setType("NUMERIC");// NUMERICAL /UNKNOWN / CATEGORICAL
		child3.setVisualAttributes(new String[] { "LEAF", "ACTIVE", "NUMERICAL" });
		child3.setMetadata(metadata_patient);

		child1.getChildren().add(child2);
		child1.getChildren().add(child3);
		
		final TreeNode child4 = new TreeNode();
		child4.setName("02. Visit Information");
		child4.setFullName("\\Demographics\\02. Visit Information\\");
		child4.setStudyId("TEST");
		child4.setType("UNKNOWN");	    
		child4.setVisualAttributes(new String[] { "FOLDER", "ACTIVE", "STUDY" });
		
		demographics.getChildren().add(child4);

		
		final TreeNode child41 = new TreeNode();
		child41.setName("Location");
		child41.setFullName("\\Demographics\\02. Visit Information\\Location\\");
		child41.setStudyId("TEST");
		child41.setType("UNKNOWN");		
		child4.setVisualAttributes(new String[] { "FOLDER", "ACTIVE", "STUDY" });
		child4.getChildren().add(child41);
		
		final TreeNode child5 = new TreeNode();
		child5.setName("Sermed");
		child5.setConceptCode("Visit.sermed");
		child5.setConceptPath("\\Demographics\\02. Visit Information\\Location\\Sermed\\");
		child5.setFullName("\\Demographics\\02. Visit Information\\Location\\Sermed\\");
		child5.setStudyId("TEST");
		child5.setType("CATEGORICAL");
		child5.setVisualAttributes(new String[] { "LEAF", "ACTIVE", "CATEGORICAL" });
		child5.setMetadata(metadata_visit);
		child41.getChildren().add(child5);
		
		final TreeNode child6 = new TreeNode();
		child6.setName("Careunit");
		child6.setConceptCode("Visit.careunit");
		child6.setConceptPath("\\Demographics\\02. Visit Information\\Location\\Careunit\\");
		child6.setFullName("\\Demographics\\02. Visit Information\\Location\\Careunit\\");
		child6.setStudyId("TEST");
		child6.setType("CATEGORICAL");
		child6.setVisualAttributes(new String[] { "LEAF", "ACTIVE", "CATEGORICAL" });

		child6.setMetadata(metadata_visit);
		child41.getChildren().add(child6);
		
		
		final TreeNode child8 = new TreeNode();
		child8.setName("Date");
		child8.setConceptCode("Visit.date");
		child8.setConceptPath("\\Demographics\\02. Patient Location\\Date\\");
		child8.setFullName("\\Demographics\\02. Patient Location\\Date\\");
		child8.setStudyId("TEST");
		child8.setType("DATE");
		child8.setMetadata(metadata_visit);
		
		child4.getChildren().add(child8);
		
		final TreeNode child9 = new TreeNode();
		child9.setName("Period");
		child9.setConceptCode("Visit.period");
		child9.setConceptPath("\\Demographics\\02. Patient Location\\Period\\");
		child9.setFullName("\\Demographics\\02. Patient Location\\Period\\");
		child9.setStudyId("TEST");
		child9.setType("CATEGORICAL");
		child9.setMetadata(metadata_visit);
		child4.getChildren().add(child9);
	    
	    return demographics;
//	    fullTreeNodes.add(demographics);
	}



	private TreeNode addTestChild() {
		TreeNode treeNode = new TreeNode();
		treeNode.setName("Test CATEGORICAL" + " (" + "9999" + ")");
		treeNode.setStudyId("TEST");
		treeNode.setFullName("\\" + "Demographics" + "\\" + "Bidon" + "\\");
		treeNode.setConceptPath(treeNode.getFullName());
//		System.err.println(treeNode.getFullName());
		treeNode.setType("CATEGORICAL");// NUMERICAL /UNKNOWN / CATEGORICAL
		treeNode.setVisualAttributes(new String[] { "LEAF", "ACTIVE", "CATEGORICAL" });
		
		
		treeNode.setConceptCode("9999");
		Metadata metadata = new Metadata();
		metadata.setSubjectDimension("patient");
		treeNode.setMetadata(metadata);
		// TODO Auto-generated method stub
		return treeNode;
	}



	private void initFullTreeNodes() throws IOException   {
		TreeNode demographics = createDemographics();
		
		Resource resource = resourceLoader.getResource("classpath:observations" + initFilePath);// work with resources in IDE. Should work in Tomcat (app/WEB-/classes) ?
		File file = resource.getFile();
		Path path = file.toPath();
		byte[] data = Files.readAllBytes(path);
		TreeNode observations = (TreeNode) SerializationUtils.deserialize(data);	
		// test 
		TreeNode snomedRootO = observations.getChildren().get(0);
//	    cleanSnomedCtBranches(snomedRootO);

		
		resource = resourceLoader.getResource("classpath:measurements" + initFilePath);// work with resources in IDE. Should work in Tomcat (app/WEB-/classes) ?
		file = resource.getFile();
		path = file.toPath();
		data = Files.readAllBytes(path);
		TreeNode measurements = (TreeNode) SerializationUtils.deserialize(data);	
		TreeNode snomedRootM = measurements.getChildren().get(0);
//	    cleanSnomedCtBranches(snomedRootM);
		
		
	    fullTreeNodes =  Arrays.asList(demographics,observations, measurements);
		

	}

	private void cleanSnomedCtBranches(TreeNode snomedRoot) {
		Iterator<TreeNode> itr = snomedRoot.getChildren().iterator();
		while (itr.hasNext()) {
			TreeNode treeNode = itr.next();
			if (treeNode.getChildren().size() == 0)
			{
				itr.remove();
			}
		}
		
	}

	private List<TreeNode> makeOntologyFromOMOP() {
//		newObjectContext = GbServerApplication.cayenneRuntime.newContext();	
//		List<Long> ids = ObjectSelect.columnQuery(Observation.class, Observation.OBSERVATION_CONCEPT_ID).distinct().select(newObjectContext);
//		List<Concept> concepts = new ArrayList<Concept>();
//		for (Long id:ids)
//		{
//			Concept concept =  ObjectSelect.query(Concept.class).where(Concept.CONCEPT_ID.eq(id)).selectOne(newObjectContext);
//			if (concept.getVocabularyId().equalsIgnoreCase("Snomed"))
//			{
//				concepts.add(concept);
//			}
//		}
//		System.err.println(concepts);
		// SNOMED CT Concept (SNOMED RT+CTV3) root Snomed CT
//		List<Concept> list = ObjectSelect.query(Concept.class).where(Concept.CONCEPT_NAME.eq("Injury of head")).select(newObjectContext);	
//		List<Concept> list = ObjectSelect.query(Concept.class).where(Concept.CONCEPT_NAME.eq("Open wound of face due to animal bite")).select(newObjectContext);// test	
		List<Concept> list = ObjectSelect.query(Concept.class).where(Concept.CONCEPT_NAME.eq("SNOMED CT Concept")).select(newObjectContext);// test	
		Concept concept = list.get(0);
		
//		Concept concept = getConcept(snomedRootCode);
		TreeNode rootTreeNode = new TreeNode();
		rootTreeNode.setName(concept.getConceptName());
		rootTreeNode.setStudyId("TEST");
		rootTreeNode.setFullName("\\" + concept.getConceptName() + "\\");
		rootTreeNode.setType("STUDY");
		rootTreeNode.setConceptCode(concept.getConceptCode());
		rootTreeNode.setVisualAttributes(new String[] { "FOLDER", "ACTIVE", "STUDY" });

		Constraint constraint1 = new Constraint();
		constraint1.setType("study_name");
		constraint1.setStudyId("TEST");
		rootTreeNode.setConstraint(constraint1);
		String[] codes = snomedRootDescendantCodes.split(";");
		
		for (String code : codes)
		{
			Concept descConcept = getConcept(code);
			TreeNode descTreeNode = new TreeNode();
			descTreeNode.setName(descConcept.getConceptName());
			descTreeNode.setStudyId("TEST");
			descTreeNode.setFullName(rootTreeNode.getFullName() + descConcept.getConceptName() + "\\");
			descTreeNode.setType("STUDY");
			descTreeNode.setConceptCode(descConcept.getConceptCode());
			descTreeNode.setVisualAttributes(new String[] { "FOLDER", "ACTIVE", "STUDY" });
//			rootTreeNode.setChildren(addElement(rootTreeNode.getChildren(), descTreeNode)); // keep at[] array. -> shout be arraylist
			rootTreeNode.getChildren().add(descTreeNode);

//	test		buildTreeRootForConcept(descConcept, descTreeNode);		
			// try to save memory
//			newObjectContext = null;
//			System.gc();
//			newObjectContext = GbServerApplication.cayenneRuntime.newContext();	
		}
		buildTreeRootForConcept(concept, rootTreeNode);
		return Arrays.asList(rootTreeNode);
	}


	private Concept getConcept(String code)
	{
		return ObjectSelect.query(Concept.class).where(Concept.CONCEPT_CODE.eq(code)).selectOne(newObjectContext);
	}
	private void buildTreeRootForConcept(Concept conceptUpLevel, TreeNode treeNodeUpLevel)
	{
		// V1 manuel select min/max = 1
		// no need as db as only min = 1 !!!
		List<ConceptAncestor> ancestorConcepts = conceptUpLevel.getAncestorConcepts();
		List<Concept> descendants = getDirectDescendant(ancestorConcepts); // to find with min/max = 1

		for (ConceptAncestor ancestorConcept : ancestorConcepts)
		{
			if (ancestorConcept.getDescendantConcept() != null)// not Snomed
				descendants.add(ancestorConcept.getDescendantConcept());
		}
		
		
		// V2 from db
//		List<Concept> descendants = getDirectDescendant(conceptUpLevel); // to find with min/max = 1
		

		// flaten relation
//		List<Concept> descendants = conceptUpLevel.getDescendants();
//		if (conceptUpLevel.getDescendants().size() > 9)
//		{
//			descendants = conceptUpLevel.getDescendants().subList(0, 10);
//		}
//		for (Concept concept : descendants)
//		{
//			if (concept == conceptUpLevel)
//				return;
			// create fror this level treeNode
		
		for (Concept concept : descendants)
		{
			
			TreeNode treeNode = new TreeNode();
			treeNode.setName(concept.getConceptName());
			treeNode.setStudyId("TEST");
			treeNode.setFullName(treeNodeUpLevel.getFullName() + concept.getConceptName() + "\\");
			System.err.println(treeNode.getFullName());
			treeNode.setType("UNKNOWN");
			treeNode.setConceptCode(concept.getConceptCode());

			Constraint constraint1 = new Constraint();
			constraint1.setType("study_name");
			constraint1.setStudyId("TEST");
			treeNode.setConstraint(constraint1);
			
			// add treenode to UpLevel
//			treeNodeUpLevel.setChildren(addElement(treeNodeUpLevel.getChildren(), treeNode)); // keep at[] array. -> shout be arraylist
			treeNodeUpLevel.getChildren().add(treeNode);
			// go down next level
			buildTreeRootForConcept(concept,treeNode);
		}
	}

	private List<Concept> getDirectDescendant(Concept conceptUpLevel) {
		List<ConceptAncestor> list = ObjectSelect.query(ConceptAncestor.class).where(
				ConceptAncestor.ASCENDANT_CONCEPT.eq(conceptUpLevel)
				.andExp(ConceptAncestor.MIN_LEVELS_OF_SEPARATION.eq(1))
				.andExp(ConceptAncestor.MAX_LEVELS_OF_SEPARATION.eq(1))
				)
				.select(conceptUpLevel.getObjectContext());	
		
		List<Concept> concepts = new ArrayList<Concept>();
		for (ConceptAncestor ca: list)
		{
			concepts.add(ca.getDescendantConcept());
		}

		return concepts;
	}

	private List<Concept> getDirectDescendant(List<ConceptAncestor> ancestorConcepts) {
		List<Concept> concepts = new ArrayList<Concept>();
		for (ConceptAncestor ancestorConcept : ancestorConcepts)
		{	
			if (ancestorConcept.getMaxLevelsOfSeparation() == 1 && ancestorConcept.getMinLevelsOfSeparation() == 1)
			{
				concepts.add(ancestorConcept.getDescendantConcept());				
			}
		}	
		return concepts;
	}
	public  TreeNodes getTreeNodes (int depth, String root)
	{
		logger.info("Service treenodes for : " + root + " " + depth);
		TreeNode start = null;
		TreeNode originalNode = null;
		TreeNode copyNode = null;

		if (ROOT_NAME.equals(root))// just send copy without children at level 2
		{
			ArrayList<TreeNode> copyNodes = new ArrayList<TreeNode>(fullTreeNodes.size());
			for (int i = 0; i < fullTreeNodes.size();i++)
			{
				originalNode = fullTreeNodes.get(i);
				copyNode = originalNode.copy();
				copyNodes.add(copyNode);
				for (TreeNode level2Node : originalNode.getChildren())
				{
					copyNode.getChildren().add(level2Node.copy());
				}
				
			}
			TreeNodes toReturn = new TreeNodes();
			toReturn.setTree_nodes(copyNodes);
			return toReturn;
		}
		else 
		{
			int level = StringUtils.countMatches(root, ROOT_NAME) - 1 ;				
			if (level == 1) // Just send the real treeNode from level 1
			{
				for (int i = 0; i < fullTreeNodes.size();i++)
				{
					start = fullTreeNodes.get(i);
					// no more needed				start = findTreeNodeForName(start,root);	
					if (start.getFullName().equals(root))
					{
						TreeNodes toReturn = new TreeNodes();
						toReturn.setTree_nodes(Arrays.asList(start));
						return toReturn;
					}
				}
			}
			else // depth node
			{
				for (int i = 0; i < fullTreeNodes.size();i++)
				{
					start = fullTreeNodes.get(i);
					start = findTreeNodeForFullName(start,root);	
					if (start != null)
					{
						TreeNodes toReturn = new TreeNodes();
						toReturn.setTree_nodes(Arrays.asList(start));
						return toReturn;
					}
				}				
			}
		}
		return new TreeNodes();
	}

	private TreeNode findTreeNodeForFullName(TreeNode node, String root) {
		   if (node.getFullName().equals(root)) {
		        return node;
		    } else {
		        for (TreeNode child: node.getChildren()) {
		        	TreeNode result = findTreeNodeForFullName(child, root);
		            if (result != null) {
		                return result;
		            }
		        }
		    }
		    return null;
	}



	public  TreeNodes getTreeNodesOld (int depth, String root)
	{	
		logger.info("Service treenodes for : " + root + " " + depth);
		TreeNode start = null;
		TreeNode copiedRoot = null;

		int level = 0;
		if (ROOT_NAME.equals(root))
		{
			start = fullTreeNodes.get(0);
		}
		else 
		{
			level = 1;
			start = fullTreeNodes.get(0);
			if (!start.getFullName().equals(root) )
			{
				start = findTreeNodeForName(start,root);	
				// compute level from  occurence number of char  "\" ????
				level = StringUtils.countMatches(start.getFullName(), ROOT_NAME) - 1 ;				
			}
		}
		copiedRoot = start.copy();
		traverseTree(start, copiedRoot, level, depth);// create a copy of TreeNode from start
		
		TreeNodes toReturn = new TreeNodes();
		toReturn.setTree_nodes(Arrays.asList(copiedRoot));
		return toReturn; 
	}
	


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
	
	public void traverseTree(TreeNode originalNode, TreeNode copiedNode,int level, int depth) {
		++level;
		if (level >= depth)
		{
			return;
		}
		int childrenCount = 0;
		if (originalNode.getChildren() != null)
		{
			childrenCount = originalNode.getChildren().size();
//			copiedNode.setChildren(new TreeNode[childrenCount]);
			copiedNode.setChildren(new ArrayList<TreeNode>(childrenCount));

		}
	    for (int i=0; i<childrenCount; i++) {
//	        TreeNode originalchild = originalNode.getChildren()[i];
	    	TreeNode originalchild = originalNode.getChildren().get(i);
	        TreeNode copiedchild = originalchild.copy();
//	        copiedNode.getChildren()[i] = copiedchild;
//	        if (i == 0)
//	        {
//	        	copiedNode.getChildren().add(copiedchild);
//	        }
//	        else
//	        	copiedNode.getChildren().set(i, copiedchild);
	        
        	copiedNode.getChildren().add(copiedchild);

//	        System.out.println(tree.toString() + counter++);
	        traverseTree(originalchild, copiedchild,level, depth);
	    }


	}
	
	private TreeNode findTreeNodeForName(TreeNode start, String name) {
		TreeNode correctNode = null ;

		int childrenCount = 0;
		if (start.getChildren() != null)
		{
//			childrenCount = start.getChildren().length;
			childrenCount = start.getChildren().size();

		}
	    for (int i=0; i<childrenCount; i++) {
//	    	TreeNode child = start.getChildren()[i];
	    	TreeNode child = start.getChildren().get(i);

	    	if (child.getFullName().equals(name))
	    	{
	    		correctNode = child;
	    	}
	    	else 
	    	{
	    		correctNode = findTreeNodeForName(child, name);
	    	}
	    }
		return correctNode;
	}
	
	// keep childen as Array []
//	static TreeNode[] addElement(TreeNode[] a, TreeNode e) {
//	    a  = Arrays.copyOf(a, a.length + 1);
//	    a[a.length - 1] = e;
//	    return a;
//	}
	private void printTrees(TreeNode node) {
		// TODO Auto-generated method stub
		testNbNodes++;
		System.err.println(node.getFullName());
		for (TreeNode aNode: node.getChildren())
		{
			printTrees(aNode);
		}
	}
}
