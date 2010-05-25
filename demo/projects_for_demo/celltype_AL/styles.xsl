<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
xmlns:mmax="org.eml.MMAX2.discourse.MMAX2DiscourseLoader"
xmlns:celltype="www.eml.org/NameSpaces/celltype">
<xsl:output method="text" indent="yes" omit-xml-declaration="yes"/>
<xsl:strip-space elements="*"/>

<xsl:template match="words">
<xsl:apply-templates/>
</xsl:template>

<xsl:template match="word">
<xsl:choose>

<xsl:when test="@id='word_1'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9915838 ***********************************************
The functional role of CrkII in actin cytoskeleton organization and mitogenesis .
Crk is a member of a family of adapter proteins predominantly composed of Src homology 2 and 3 domains , whose role in signaling pathways is presently unclear .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_52'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

17 nM insulin stimulation dissociated the binding of c-CrkII to p130(cas ) , whereas 13 nM insulin - like growth factor-I , 16 nM epidermal growth factor ( EGF ) , and 10 % serum each showed little or no effect .
We found that stress fiber formation is consistent with a change in the p130(cas ) .c-CrkII
interactions before and after growth factor stimulation .
Microinjection of either GST-Crk -SH2 or - Crk-(N)SH3 domains , or anti - Crk antibody each inhibited stress fiber formation before and after insulin - like growth factor-I , EGF , and serum stimulation .
Insulin stimulation by itself caused stress fiber breakdown and there was no additive effect of microinjection .
Microinjection of anti - p130(cas ) antibody also blocked stress fiber formation in quiescent cells .
Microinjection of the Crk - inhibitory reagents also inhibited DNA synthesis after insulin - like growth factor-I , EGF , and serum stimulation , but not after insulin .
These data suggest that the complex containing p130(cas ) .c-CrkII may play a crucial role in actin cytoskeleton organization and in anchorage - dependent DNA synthesis .
******************************* END ABSTRACT 9915838 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_53'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9915850 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_70'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Ligation of either CD80 ( B7-1 ) or CD86 ( B7-2 ) , two principal ligands for CD28 , is thought to skew the immune response toward Th1 or Th2 differentiation .
We have examined early signal transduction pathways recruited following T cell stimulation with either CD80 or CD86 .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_112'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

In the presence of phorbol 12-myristate 13-acetate , both CHO-CD80 and CHO-CD86 , like anti - CD28 mAb , were capable of stimulating cytokine production from both human peripheral T cells and Jurkat T cells .
Both CHO-CD80 and CHO-CD86 , in the presence of anti - CD3 mAb , costimulated NFAT - dependent transcriptional activation .
Several intracellular signaling proteins , such as CBL and VAV , were phosphorylated on tyrosine in response to CD80 , CD86 , and anti - CD28 mAb .
Surprisingly , although stimulation of Jurkat T cells with either CHO-CD80 or anti - CD28 mAb resulted in robust tyrosine phosphorylation of CD28 itself , ligation with CHO-CD86 was unable to induce detectable CD28 tyrosyl phosphorylation over a range of stimulation conditions .
In addition , the association of phosphoinositide 3-kinase with CD28 and enhanced tyrosine phosphorylation of phospholipase Cgamma were seen after anti - CD28 mAb and CHO-CD80 stimulation but to a much lesser extent after CHO-CD86 stimulation .
Thus , ligation of CD28 with either CD80 or CD86 leads to shared early signal transduction events such as the tyrosine phosphorylation of CBL and VAV , to NFAT - mediated transcriptional activation , and to the costimulation of interleukin-2 and granulocyte - macrophage colony - stimulating factor production .
However , CD80 and CD86 also induce distinct signal transduction pathways including the tyrosine phosphorylation of CD28 and phospholipase Cgamma1 and the SH2 - dependent association of phosphoinositide 3-kinase with CD28 .
These quantitative , if not qualitative , differences between signaling initiated by these two ligands for CD28 may contribute to functional differences ( e.g. Th1 or Th2 differentiation ) in T cell responses .
******************************* END ABSTRACT 9915850 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_113'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9915860 ***********************************************
Sp1 and egr-1 have opposing effects on the regulation of the rat Pgp2 / mdr1b gene .
The promoter of the rat pgp2 / mdr1b gene has a GC -rich region ( pgp2GC ) that is highly conserved in mdr genes and contains an consensus Sp1 site .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_130'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

The pgp2 / mdr1b promoter was strongly activated by co - transfected wild type Sp1 but not mutant Sp1 and mutation of the Sp1 site abrogated Sp1 - dependent transactivation .
In gel shift assays , the same mutations abolished Sp1-DNA complex formation .
Moreover , basal activity of the pgp2 / mdr1b Sp1 mutant promoter was dramatically lower .
Enforced ectopic overexpression of Sp1 in H35 rat hepatoma cells revealed that cell lines overexpressing Sp1 had increased endogenous pgp2 / mdr1b mRNA , demonstrating that Sp1 activates the endogenous pgp2 / mdr1b gene .
Pgp2GC oligonucleotide also bound Egr-1 in gel shift assays and Egr-1 competitively displaced bound Sp1 .
In transient transfections of H35 cells ( and human LS180 and HepG2 cells ) Egr-1 potently and specifically suppressed pgp2 / mdr1b promoter activity and mutations in the Egr-1 site decreased Egr-1 binding and correlated with pgp2 / mdr1b up -regulation .
Ectopic overexpression of Egr-1 in H35 cells decreased Pgp expression and selectively increased vinblastine sensitivity .
In conclusion , Sp1 positively regulates while Egr-1 negatively regulates the rat pgp2 / mdr1b gene .
Moreover , competitive interactions between Sp1 and Egr-1 in all likelihood determine the constitutive expression of the pgp2 / mdr1b gene in H35 cells .
******************************* END ABSTRACT 9915860 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_131'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9915849 ***********************************************
Functional coupling between various phospholipase A2s and cyclooxygenases in immediate and delayed prostanoid biosynthetic pathways .
Several distinct phospholipase A2s ( PLA2s ) and two cyclooxygenases ( COXs ) were transfected , alone or in combination , into human embryonic kidney 293 cells , and their functional coupling during immediate and delayed prostaglandin ( PG) - biosynthetic responses was reconstituted .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_208'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Experiments on cells transfected with either COX alone revealed subtle differences between the PG - biosynthetic properties of the two isozymes in that COX-1 and COX-2 were favored over the other in the presence of high and low exogenous AA concentrations , respectively .
Moreover , COX-2 , but not COX-1 , could turn on endogenous AA release , which was inhibited by a cPLA2 inhibitor .
When PLA2 and COX were coexpressed , AA released by cPLA2 , sPLA2-IIA and sPLA2 -V was converted to PGE2 by both COX-1 and COX-2 during the immediate response and predominantly by COX-2 during the delayed response .
Ca2+ - independent PLA2 ( iPLA2 ) ( type VI ) , which plays a crucial role in phospholipid remodeling , failed to couple with COX-2 during the delayed response , whereas it was linked to ionophore - induced immediate PGE2 generation via COX-1 in marked preference to COX-2 .
Finally , coculture of PLA2 and COX transfectants revealed that extracellular sPLA2s-IIA and - V , but neither intracellular cPLA2 nor iPLA2 , augmented PGE2 generation by neighboring COX- expressing cells , implying that the heparin - binding sPLA2s play a particular role as paracrine amplifiers of the PG - biosynthetic response signal from one cell to another .
******************************* END ABSTRACT 9915849 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_209'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9914159 ***********************************************
The KDEL retrieval system is exploited by Pseudomonas exotoxin A , but not by Shiga - like toxin-1 , during retrograde transport from the Golgi complex to the endoplasmic reticulum .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_262'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Expression of the lysozyme variants and the KDEL receptor was confirmed by immunofluorescence .
When such cells were challenged with diphtheria toxin ( DT ) or Escherichia coli Shiga - like toxin 1 ( SLT-1 ) , there was no observable difference in their sensitivities as compared to cells which did not express these exogenous proteins .
By contrast , the cytotoxicity of Pseudomonas exotoxin A ( PE ) is reduced by expressing lysozyme-KDEL , which causes a redistribution of the KDEL receptor from the Golgi complex to the ER , and cells are sensitised to this toxin when they express additional KDEL receptors .
These data suggest that , in contrast to SLT-1 , PE can exploit the KDEL receptor in order to reach the ER lumen where it is believed that membrane transfer to the cytosol occurs .
This contention was confirmed by microinjecting into Vero cells antibodies raised against the cytoplasmically exposed tail of the KDEL receptor .
Immunofluorescence confirmed that these antibodies prevented the retrograde transport of the KDEL receptor from the Golgi complex to the ER , and this in turn reduced the cytotoxicity of PE , but not that of SLT-1 , to these cells .
******************************* END ABSTRACT 9914159 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_263'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9918920 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_280'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Amyloid fibrils of diverse origin are known to disturb vital cellular functions and induce cell death .
In this study , the effects of amyloid fibrils from the C - terminal fragment ( C-36 ) of cleaved alpha1 -antitrypsin ( AAT ) on low - density lipoprotein ( LDL ) metabolism were investigated in HepG2 cells .
Treatment of the cells with C-36 fibrils ( 10 micromol / L ) enhanced 125I-LDL binding and uptake 10 to 15 times , and highly up -regulated levels of LDL receptor mRNA , as compared with control cells .
Competition experiments using excess of unlabeled LDL and blockage experiments with a monoclonal LDL receptor antibody diminished or completely abolished the stimulatory effects of fibrils on LDL binding and LDL receptor mRNA levels , suggesting that fibrils act via the LDL receptor pathway .
However , C-36 fibrils had no significant effect on [2-14C]acetate incorporation into cholesterol biosynthesis and cholesterol ester formation , but inhibited 125I-LDL degradation by 20 % and reduced bile acid biosynthesis up to 48 % in a dose - dependent manner .
Preincubation of the cells with fibrils before the addition of LDL totally abolished the LDL inhibitory effect on unesterified cholesterol synthesis , further confirming the LDL receptors to be the target for C-36 fibrils .
Moreover , the expression of sterol regulatory element binding protein-1 ( SREBP -1 ) was found to increase twofold and more after 24 hours of incubation of the cells with several concentrations of C-36 fibrils .
Our study suggests that the cytotoxicity of C-36 fibrils on HepG2 cells is associated with perturbed intracellular cholesterol homeostasis , induced through fibril - stimulated expression of the LDL receptors via the sterol - responsive element .
******************************* END ABSTRACT 9918920 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_281'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9920902 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_297'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

AE1 is the chloride / bicarbonate anion exchanger of the erythrocyte plasma membrane .
We have used scanning cysteine mutagenesis and sulfhydryl - specific chemistry to identify pore - lining residues in the Ser643-Ser690 region of the protein .
The Ser643-Ser690 region spans transmembrane segment 8 of AE1 and surrounds Glu681 , which may reside at the transmembrane permeability barrier .
Glu681 also directly interacts with some anions during anion transport .
The introduced cysteine mutants were expressed by transient transfection of HEK293 cells .
Anion exchange activity was assessed by measurement of changes of intracellular pH , which follow transmembrane bicarbonate movement mediated by AE1 .
To identify residues that might form part of an aqueous transmembrane pore , we measured anion exchange activity of each introduced cysteine mutant before and after incubation with the sulfhydryl reagents para-chloromercuribenzene sulfonate and 2-(aminoethyl)methanethiosulfonate hydrobromide .
Our data identified transmembrane mutants A666C , S667C , L669C , L673C , L677C , and L680C and intracellular mutants I684C and I688C that could be inhibited by sulfhydryl reagents and may therefore form a part of a transmembrane pore .
These residues map to one face of a helical wheel plot .
The ability to inhibit two intracellular mutants suggests that transmembrane helix 8 extends at least two helical turns beyond the intracellular membrane surface .
The identified hydrophobic pore - lining residues ( leucine , isoleucine , and alanine ) may limit interactions with substrate anions .
******************************* END ABSTRACT 9920902 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_298'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9914486 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_311'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Elevated levels of the urokinase - type plasminogen activator ( uPA ) in tumor cells are conductive to tumor cell spread and metastasis .
In a previous study we observed that suppression of RelA dramatically reduced endogenous uPA synthesis in the human ovarian cancer cell line OV-MZ-6.
Because the uPA promoter contains three potential Rel - like protein binding motifs ( RRBE , 5 '-NF-kappaB , and 3'-NF-kappaB ) we conducted the first thorough systematic uPA promoter analysis to examine the direct impact of Rel proteins on uPA gene transcription .
Disruption of RRBE resulted in a approximately 40 % decrease in uPA promoter activity , mutation of the 5 '-NF-kappaB motif led to an additional 20 % decrease .
The 3'-NF-kappaB motif was not active .
Overexpression of RelA significantly enhanced uPA promoter activity , whereas IkappaB-alpha overexpression reduced uPA promoter activity by 40 % .
These data were supported by the finding that endogenous uPA was also increased sixfold by overexpression of RelA and decreased by 30 % upon overexpression of IkappaB-alpha .
Transfection of OV-MZ-6 cells with antisense deoxynucleotides directed to RelA expression reduced uPA promoter activity by at least 40 % .
Our data clearly suggest that by binding to uPA promoter elements , Rel transcripton factors contribute directly to elevated uPA gene expression in human ovarian cancer cells , thereby promoting the multiple functions of uPA during tumor growth and metastasis .
******************************* END ABSTRACT 9914486 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_312'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9920756 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_329'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Wilms ' tumor is associated with mutations of WT1 , a zinc -finger transcription factor that is essential for the development of the metanephric kidney and the urogenital system .
High levels of WT1 expression also have been detected in myeloid leukemia cells , suggesting that WT1 may be important in other neoplasms as well .
To seek a role of high level expression of WT1 in the differential arrest characteristic of myeloid leukemia , WT1 or its zinc -finger domain alone was stably expressed in human promyeloid leukemia ( HL-60 ) cells and the ability of 12-O-tetradecanoyl-phorbol-13-acetate ( TPA ) to induce macrophage differentiation was examined .
HL-60 cell differentiation was completely arrested in TPA treated cells that expressed WT1 or its zinc -finger domain alone whereas TPA fully induced macrophage differentiation in control HL-60 cells , indicating that high level expression of WT1 is capable of differentiation arrest of myeloid cells and that its effect may be mediated through its zinc -finger domain .
To determine if the zinc -finger domain of WT1 directly influences transcription , it was brought to promoter DNA as a fusion protein with the Gal4 DNA binding domain .
The fusion protein failed to regulate transcription of a reporter gene but when the zinc -finger domain of WT1 was brought to DNA with a promoter containing two upstream WT1 - binding sites , reporter gene expression was activated approximately threefold , suggesting that WT1 interferes with myeloid differentiation through the ability of its zinc -finger domain to compete with other transcription factors for common promoter elements .
******************************* END ABSTRACT 9920756 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_330'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9987010 ***********************************************
GABAC receptor rho subunits are heterogeneously expressed in the human CNS and form homo - and heterooligomers with distinct physical properties .
In the central nervous system , receptors for gamma-aminobutyric acid ( GABA ) are responsible for inhibitory neurotransmission .
Anatomical and electrophysiological studies indicate that GABAC receptors are composed of rho subunits .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_359'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

By RT - PCR , we demonstrated that rho 1 expression is primarily restricted to the retina , whereas the rho 2 subunit was present in all brain regions tested .
Transfection of HEK-293 cells with rho 2 cDNA resulted in GABA - gated whole - cell currents that differed from those mediated by the rho 1 subunit in two respects : maximal amplitude ( rho 1 :rho 2 approximately 4 :1) and inactivation time course ( rho 1 :rho 2 approximately 2 :1 ) .
Cotransfection of rho 1 and rho 2 cDNA in a 1 :1 ratio generated whole - cell currents with large amplitudes characteristic of rho 1 but more rapid inactivation typical for rho 2 .
This observation suggested formation of heterooligomeric GABAC receptors with distinct features .
Therefore , we tested the assembly of rho 1 and rho 2 subunits by cotransfecting rho 2 cDNA together with a chimeric rho 1 beta 1 subunit , known to interfere with rho 1 assembly in a dominant - negative fashion .
Reduction of rho 2 generated currents correlated with the ratio of chimeric to rho 2 cDNA .
Secondly , we determined that the picrotoxinin sensitivity of cells transfected with various ratios of rho 1 and rho 2 cDNA differed from that expected of a pure mixture of homooligomeric receptors .
The latter two observations support the idea that rho 1 and rho 2 subunits form heterooligomeric GABAC receptors in mammalian cells .
Together , our results indicate that the presence of both rho subunits enables the formation of heterooligomeric receptors with physical properties distinct from homooligomers , thus increasing the diversity of GABAC receptors in the CNS .
******************************* END ABSTRACT 9987010 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_360'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9922122 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_374'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Normal human diploid cells have a limited proliferative lifespan in in vitro cultures .
Changes in gene expression have been examined for understanding control mechanisms of limited proliferative lifespan . and enhanced expression of growth suppressing genes such as p21 was reported in late - passaged cells .
We screened genes which were expressed preferentially in mid - passaged cells by the differential plaque screening of the subtracted cDNA libraries prepared from young , life- extended , and immortalized SV40 - transformed human fibroblasts .
Among isolated clones , ASF / SF2 , which was known to affect alternative splicing , was expressed in normal fibroblasts with a peak at mid - passage .
Relative expression levels of SC35 and hnRNPA1 , which are also known to affect alternative splicing , was also highest at mid - passage .
Changes in alternative splicing at mid - passage , if it occurred , may play a crucial role in the process of cellular senescence .
******************************* END ABSTRACT 9922122 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_375'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9920410 ***********************************************
Calcium - binding protein S100A4 in health and disease .
The S100 proteins contain two EF-hand motifs and are of generally unknown function .
One of these proteins , S100A4 , is an intracellular calcium - binding protein that is present in normal rodent and human cells .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_399'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Similarly , in human breast cell lines , S100A4 is present at a higher level in cultured cells from the more malignant , than in those from the more benign tumours .
Gene transfer experiments have shown that rodent or human S100A4 is able to induce metastatic capability in otherwise non - metastatic breast tumour cells .
Furthermore , expression of rodent S100A4 transgenes can induce metastasis of benign tumours arising in transgenic model systems .
Possible mechanisms for the metastasis - inducing effect of S100A4 and the relevance of these observations to human cancer are discussed .
******************************* END ABSTRACT 9920410 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_400'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9914474 ***********************************************
Distinct promoters control transmembrane and cytosolic protein tyrosine phosphatase epsilon expression during macrophage differentiation .
We have recently isolated two cDNAs encoding two forms of transmembrane and cytosolic protein tyrosine phosphatase epsilon ( PTPepsilon ) .
In this study , the 5 ' end of the rat PTPepsilon gene was isolated and characterized .
Transmembrane PTPepsilon ( PTPepsilonM ) and cytosolic PTPepsilon ( PTPepsilonC ) were encoded by a single gene .
5 ' RACE analysis and RNase protection assay showed that the mRNA of each PTPepsilon isoform was transcribed from different promoters .
The putative promoter regions of two alternative first exons lacked a TATA box , but contained potential recognition sites for several transcription factors .
Reverse transcription PCR analysis revealed that PTPepsilonC mRNA was up -regulated during interleukin 6 - induced differentiation of murine leukemia M1 cells , whereas PTPepsilonM mRNA was down - regulated .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_432'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

In the differentiated HL-60 cells , the activity of the PTPepsilonC promoter , but not that of PTPepsilonM , was dramatically elevated .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_452'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

These results suggest that the different promoters control expression of PTPepsilon isoforms during the differentiation and/or activation of macrophages .
******************************* END ABSTRACT 9914474 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_453'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9920279 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_474'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Nuclear factor - kappaB ( NF-kappaB ) / Rel designates a family of transcription factors participating in the activation of a wide range of genes crucially involved in immune and inflammatory function .
NF-kappaB / Rel proteins have been demonstrated recently in primary neurons and in several brain areas .
Functional significance of these proteins is still not understood completely , but since certain subsets of neurons appear to contain constitutively active DNA - binding activity , it seems likely that they may participate in normal brain function .
A growing body of evidence is accumulating for a specific activation of NF-kappaB / Rel proteins in the CNS , and in particular in neuronal cells , during neurodegenerative processes associated to etiologically unrelated conditions .
Whether NF-kappaB activation is part of the neurodegenerative process or of protective mechanisms is a matter of debate .
This issue will be reviewed here with particular attention to the available reports on the activity of NF-kappaB / Rel proteins in both experimental paradigms of neurodegeneration and post - mortem brain tissue of patients affected by various neurological diseases .
We hypothesize that NF-kappaB / Rel proteins may represent the point of convergence of several signalling pathways relevant for initiating or accelerating the process of neuronal dysfunction and degeneration in many neurological diseases , including Parkinson 's disease , Alzheimer 's disease , CNS viral infections , and possibly others .
If NF-kappaB / Rel proteins represent an integrating point of several pathways potentially contributing to neuronal degeneration , molecules that finely modulate their activity could represent a novel pharmacological approach to several neurological diseases .
******************************* END ABSTRACT 9920279 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_475'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9918857 ***********************************************
p85 subunit of PI3 kinase does not bind to human Flt3 receptor , but associates with SHP2 , SHIP , and a tyrosine -phosphorylated 100 -kDa protein in Flt3 ligand - stimulated hematopoietic cells .
Flt3 / Flk2 belongs to class III receptor tyrosine kinases .
Like other members of type III receptor tyrosine kinases , murine Flt3 induces tyrosine phosphorylation of p85 and subsequently activation of PI3 kinase upon FL binding .
While p85 binds murine Flt3 at Y958 in the carboxyl terminus of the receptor , human Flt3 does not have a potential p85 - binding site in the carboxyl terminus .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_495'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

In contrast to murine Flt3 , p85 is not tyrosine phosphorylated after FL stimulation , nor does it bind Flt3 in both cell lines .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_531'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

The p100 associates with both p85 and SHP-2 .
In THP-1 cells , p85 associates inducibly with tyrosine phosphorylated SHIP , p100 and p120 .
These results indicate that p85 does not bind human Flt3 , but forms a complex with SHP-2 , SHIP , p100 and p120 in hematopoietic cells .
******************************* END ABSTRACT 9918857 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_532'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9988683 ***********************************************
Mitogenic up -regulation of the PRL-1 protein - tyrosine phosphatase gene by Egr-1 .
Egr-1 activation is an early event in liver regeneration .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_542'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Insight could be provided by understanding the signals regulating the transcriptional induction of immediate -early genes which occurs within minutes of the growth stimulus .
The expression of the PRL-1 gene , which encodes a unique nuclear protein - tyrosine phosphatase , is rapidly induced in regenerating liver and mitogen - treated cells .
Transcription of the PRL-1 gene increased in the rat liver remnant within a few minutes after partial hepatectomy and largely explained the increase in steady - state PRL-1 mRNA in the first few hours posthepatectomy .
Egr-1 ( early growth response factor ) specifically bound a region of the proximal PRL-1 promoter P1 ( -99 ) .
Egr-1 binding activity was more rapidly induced in regenerating liver than mitogen - treated H35 and NIH 3T3 cells , remained elevated through 4 h posthepatectomy , and appeared to be dependent not only on new Egr-1 protein synthesis but on post - translational regulation of Egr-1 .
Egr-1 efficiently transactivated a PRL-1 promoter reporter construct containing an intact not mutant Egr-1 site , and the Egr-1 site largely accounted for PRL-1 gene up -regulation in response to mitogen stimulation .
These data predict that Egr-1 activation is an early event in liver regeneration and mitogen - activated cells that provides a regulatory stimulus for a subset of immediate -early genes .
******************************* END ABSTRACT 9988683 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_543'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9920505 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_563'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Proliferation of vascular smooth muscle cells contributes to initimal hyperplasia during atherogenesis , but the factors regulating their proliferation are not well known .
In the present study we report that sublytic C5b-9 assembly induced proliferation of differentiated human aortic smooth muscle cells ( ASMC ) in culture .
Cell cycle re- entry occurred through activation of cdk4 , cdk2 kinase and the reduction of p21 cell cycle inhibitor .
We also investigated if C5b-9 cell cycle induction is mediated through activation of mitogen activated protein kinase ( MAPK ) pathways .
Extracellular signal regulated kinase ( ERK ) 1 activity was significantly increased , while c-jun NH2 - terminal kinase ( JNK ) 1 and p38 MAPK activity were only transiently increased .
Pretreatment with wortmannin inhibits ERK1 activation by C5b-9 , suggesting the involvement of phosphatidylinositol 3-kinase ( PI 3-kinase ) .
Both PI 3-kinase and p70 S6 kinase were activated by C5b-9 but not by C5b6 .
C5b-9 induced DNA synthesis was abolished by pretreatment with inhibitors of ERK1 and PI 3-kinase , but not by p38 MAPK .
These data indicated that ERK1 and PI 3-kinase play a major role in C5b-9 induced ASMC proliferation .
******************************* END ABSTRACT 9920505 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_564'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9973488 ***********************************************
Molecular mechanisms and selection influence the generation of the human V lambda J lambda repertoire .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_591'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Of the 30 known functional V lambda genes , 23 were detected in either the nonproductive or productive repertoires .
Specific V lambda genes , including 2A2 , 2B2 , 1G , and 4B , were overexpressed in the nonproductive repertoire , whereas some V lambda genes , such as 3R , 2A2 , 2B2 , 1C , 1G , and 1B , were overexpressed in the productive repertoire .
Comparison of the nonproductive and productive repertoires indicated that no V lambda genes were positively selected , whereas a number of V lambda genes , including 4C , 1G , 5B , and 4B , were negatively regulated .
All four of the functional J lambda segments were found in both repertoires , with J lambda 7 observed most often .
Evidence of terminal deoxynucleotidyltransferase activity was noted in nearly 80 % of nonproductive V lambda J lambda rearrangements , and exonuclease activity was apparent in the majority .
Despite this , the mean CDR3 length was 30 base pairs in both productive and nonproductive repertoires , suggesting that it was tightly regulated at the molecular level .
These results have provided new insights into the dimensions of the human V lambda repertoire and the influences that shape it .
******************************* END ABSTRACT 9973488 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_592'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9973595 ***********************************************
Formation of a primitive ectoderm like cell population , EPL cells , from ES cells in response to biologically derived factors .
The primitive ectoderm of the mouse embryo arises from the inner cell mass between 4.75 and 5.25 days post coitum , around the time of implantation .
Positioned at a pivotal time in development , just prior to formation of the three germ layers of the embryo proper , the primitive ectoderm responds directly to the signals generated during gastrulation .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_626'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

EPL cells expressed the pluripotent cell markers Oct4 , SSEA1 and alkaline phosphatase .
However , the formation of EPL cells was accompanied by alterations in Fgf5 , Gbx2 and Rex1 expression , a loss in chimaera forming ability , changes in factor responsiveness and modified differentiation capabilities , all consistent with the identification of EPL cells as equivalent to the primitive ectoderm population of the 5.5 to 6.0 days post coitum embryo .
EPL cell formation could be reversed in the presence of LIF and withdrawal of MEDII , which suggested that EPL cell formation was not a terminal differentiation event but reflected the ability of pluripotent cells to adopt distinct cell states in response to specific factors .
Partial purification of MEDII revealed the presence of two separable biological activities , both of which were required for the induction and maintenance of EPL cells .
We show here the first demonstration of uniform differentiation of ES cells in response to biological factors .
The formation of primitive ectoderm , both in vivo and in vitro , appears to be an obligatory step in the differentiation of the inner cell mass or ES cells into cell lineages of the embryonic germ layers .
EPL cells potentially represent a model for the development of lineage specific differentiation protocols and analysis of gastrulation at a molecular level .
An understanding of the active components of MEDII may provide a route for the identification of factors which induce primitive ectoderm formation in vivo .
******************************* END ABSTRACT 9973595 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_627'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9920833 ***********************************************
A secreted proform of neutrophil proteinase 3 regulates the proliferation of granulopoietic progenitor cells .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_674'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

We isolated an active 125-kD component of LAI from HL-60 conditioned medium ( CM ) , subjected it to cyanogen bromide cleavage and show by amino acid sequencing of the resulting peptides that it consists of a complex of the serine proteinase inhibitor alpha1 -antitrypsin and a 31 -kD fragment that retained the S-phase inhibitory activity , but resisted sequencing .
This finding suggested that the 31 -kD fragment originated from one of the neutrophil serine proteases ( ie , elastase , proteinase 3 , or cathepsin G ) produced by normal promyelocytes , as well as HL-60 cells , for storage in primary granules and partly secreted during synthesis as enzymatically inactive proforms .
Immunoblot analysis showed that the 125-kD complex contained proteinase 3 ( PR3 ) , and immunoprecipitation of PR3 from HL-60 CM abrogated the S-phase inhibitory activity , whereas immunoprecipitation of cathepsin G or elastase did not .
Immunoprecipitation of PR3 from CM of a subpopulation of normal marrow cells also abrogated the S-phase inhibitory effect .
Furthermore , CM from rat RBL and murine 32D cell lines transfected with human PR3 both reduced the fraction of CFU-GM in S-phase with 30 % to 80 % at 1 to 35 ng / mL PR3 , whereas CM of the same cells transfected with cathepsin G or elastase did not .
Also , an enzymatically silent mutant of PR3 exerted full activity , showing that the S-phase modulatory effect is not dependent on proteolytic activity .
Amino acid sequencing of biosynthetically radiolabeled PR3 showed that PR3 from transfected cells is secreted after synthesis as proforms retaining amino terminal propeptides .
In contrast , mature PR3 extracted from mature neutrophils has only minor activity .
The inhibitory effect of secreted PR3 is reversible and abrogated by granulocyte ( G) - or granulocyte - macrophage colony - stimulating factor ( GM -CSF ) .
Experiments with highly purified CD34( + ) bone marrow cells suggested that PR3 acts directly on the granulopoietic progenitor cells .
These observations suggest a role for PR3 in regulation of granulopoiesis , and possibly in suppression of normal granulopoiesis in leukemia .
******************************* END ABSTRACT 9920833 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_675'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9914162 ***********************************************
The Ku70 autoantigen interacts with p40phox in B lymphocytes .
Ku70 , a regulatory component of the DNA - dependent protein kinase , was identified by a yeast two -hybrid screen of a B lymphocyte cDNA library as a partner of p40phox , a regulatory component of the O2 -- producing NADPH oxidase .
Truncated constructs of p40phox and Ku70 were used to map the interacting sites .
The 186 C - terminal amino acids ( aa ) of Ku70 were found to interact with two distinct regions of p40phox , the central core region ( aa 50 - 260 ) and the C - terminal extremity ( aa 260 -339 ) .
In complementary experiments , it was observed that Ku70 binds to immobilized recombinant p40phox fusion protein and that p40phox and Ku70 from a B lymphocyte cell extract comigrate in successive chromatographies on Q Separose , Superose 12 and hydroxylapatite columns .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_694'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>


</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_731'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>


</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_785'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

******************************* END ABSTRACT 9914162 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_786'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9988757 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_807'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Glutathione ( GSH ) is an important physiological antioxidant in lung epithelial cells and lung lining fluid .
We studied the regulation of GSH synthesis in response to the pro - inflammatory cytokine tumor necrosis factor - alpha ( TNF-alpha ) and the anti - inflammatory agent dexamethasone in human alveolar epithelial cells ( A549 ) .
TNF-alpha ( 10 ng / ml ) exposure increased GSH levels , concomitant with a significant increase in gamma-glutamylcysteine synthetase ( gamma- GCS ) activity and the expression of gamma-GCS heavy subunit ( gamma- GCS-HS ) mRNA at 24 h .
Treatment with TNF-alpha also increased chloramphenicol acetyltransferase ( CAT ) activity of a gamma-GCS-HS 5 ' -flanking region reporter construct , transfected into alveolar epithelial cells .
Mutation of the putative proximal AP-1- binding site ( -269 to - 263 base pairs ) , abolished TNF-alpha- mediated activation of the promoter .
Gel shift and supershift analysis showed that TNF-alpha increased AP-1 DNA binding which was predominantly formed by dimers of c-Jun .
Dexamethasone ( 3 microM ) produced a significant decrease in the levels of GSH , decreased gamma-GCS activity and gamma-GCS-HS mRNA expression at 24 h .
The increase in GSH levels , gamma-GCS-HS mRNA , gamma-GCS-HS promoter activity , and AP-1 DNA binding produced by TNF-alpha were abrogated by co - treating the cells with dexamethasone .
Thus these data demonstrate that TNF-alpha and dexamethasone modulate GSH levels and gamma-GCS-HS mRNA expression by their effects on AP-1 ( c-Jun homodimer ) .
These data have implications for the oxidant /antioxidant balance in inflammatory lung diseases .
******************************* END ABSTRACT 9988757 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_808'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9914532 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_826'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Human meprin ( N-benzoyl- l-tyrosyl-p-aminobenzoic acid hydrolase , EC 3.4.24.18 ) , an astacin - type metalloprotease , is expressed by intestinal epithelial cells as a dimeric protein complex of alpha and beta subunits .
In transfected cells , intracellular proteolytic removal of the membrane anchor from the alpha subunit results in its secretion , while the beta subunit and alpha/ beta heterodimers are retained at the cell membrane .
We investigated the consequence of differential intracellular processing of alpha and beta subunits in the human small and large intestine using subunit - specific immunohistochemistry , in situ hybridization and biosynthetic studies in organ culture .
In the ileum , both subunits localize to the brush - border membrane of villus enterocytes .
In contrast , the beta subunit is not expressed in the colon , which leads to the secretion of the alpha subunit .
We conclude that differential expression of meprin alpha and beta subunits is a unique means of targeting the proteolytic activity of the alpha subunit either to the brush - border membrane in the ileum or to the lumen in the colon , suggesting dual functions of cell - associated and luminal meprin .
Meprin alpha and beta subunits are also coexpressed in distinct lamina propria leukocytes , suggesting an additional role for this protease in leukocyte function in the intestinal mucosa .
******************************* END ABSTRACT 9914532 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_827'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9974215 ***********************************************
Modulation of osteogenic differentiation in human skeletal cells in Vitro by 5-azacytidine .
Cellular differentiation is controlled by a variety of factors including gene methylation , which represses particular genes as cell fate is determined .
The incorporation of 5-azacytidine ( 5azaC ) into DNA in vitro prevents methylation and thus can alter cellular differentiation pathways .
Human bone marrow fibroblasts and MG63 cells treated with 5azaC were used as models of osteogenic progenitors and of a more mature osteoblast phenotype , respectively .
The capacity for differentiation of these cells following treatment with glucocorticoids was investigated .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_868'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

MG63 cells represent a phenotype late in the osteogenic lineage in which demethylation is sufficient to induce alkaline phosphatase activity .
Marrow fibroblasts are at an earlier stage of differentiation and require stimulation with glucocorticoids .
In contrast , the expression of osteocalcin , an osteoblastic marker , was unaffected by 5azaC treatment , suggesting that regulation of expression of the osteocalcin gene does not involve methylation .
These models provide novel approaches to the study of the control of differentiation in the marrow fibroblastic system .
******************************* END ABSTRACT 9974215 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_869'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9915837 ***********************************************
Pyropheophorbide - a methyl ester - mediated photosensitization activates transcription factor NF-kappaB through the interleukin-1 receptor - dependent signaling pathway .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_887'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

We demonstrated that PPME photosensitization activated NF-kappaB transcription factor in colon cancer cells .
Unexpectedly , this activation occurred in two separate waves , i.e . a rapid and transient one and a second slower but sustained phase .
The former was due to photosensitization by PPME localized in the cytoplasmic membrane which triggered interleukin-1 receptor internalization and the transduction pathways controlled by the interleukin-1 type I receptor .
Indeed , TRAF6 dominant negative mutant abolished NF-kappaB activation by PPME photosensitization , and TRAF2 dominant negative mutant was without any effect , and overexpression of IkappaB kinases increased gene transcription controlled by NF-kappaB .
Oxidative stress was not likely involved in the activation .
On the other hand , the slower and sustained wave could be the product of the release of ceramide through activation of the acidic sphingomyelinase .
PPME localization within the lysosomal membrane could explain why ceramide acted as second messenger in NF-kappaB activation by PPME photosensitization .
These data will allow a better understanding of the molecular basis of tumor eradication by photodynamic therapy , in particular the importance of the host cell response in the treatment .
******************************* END ABSTRACT 9915837 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_888'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9914176 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_901'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

The understanding of molecular mechanisms regulating the formation , growth and differentiation of haemopoietic stem cells has advanced considerably recently .
Particular progress has been made in defining the cytokines , chemokines and extracellular matrix components which retain and maintain primitive haemopoietic cell populations in bone marrow .
Furthermore , signal transduction pathways that are critical for haemopoiesis , both in vivo and in vitro , and that are activated by cytokines have also been identified and further characterised .
The importance of these processes has , this year , been exemplified by the phenotypes of mice deficient in key signal transduction proteins and the discovery that mutations in the component proteins of some signalling pathways are linked to human diseases .
Significant advances in understanding the molecular mechanisms for mobilisation of stem cells from bone marrow have also been made this year ; this has potential importance for bone marrow transplantation .
******************************* END ABSTRACT 9914176 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_902'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9989781 ***********************************************
Non - transferrin - bound iron uptake in Belgrade and normal rat erythroid cells .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_914'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Transferrin ( Tf) - dependent iron uptake is defective because of a mutation in DMT1 ( Nramp2 ) , blocking endosomal iron efflux .
This experiment of nature permits the present study to address whether the mutation also affects non - Tf - bound iron ( NTBI ) uptake and to use NTBI uptake compared to Tf-Fe utilization to increase understanding of the phenotype of the b mutation .
The distribution of 59Fe2 + into intact erythroid cells and cytosolic , stromal , heme , and nonheme fractions was different after NTBI uptake vs. Tf-Fe uptake , with the former exhibiting less iron into heme but more into stromal and nonheme fractions .
Both reticulocytes and erythrocytes exhibit NTBI uptake .
Only reticulocytes had heme incorporation after NTBI uptake .
Properly normalized , incorporation into b/ b heme was approximately 20 % of + /b , a decrease similar to that for Tf-Fe utilization .
NTBI uptake into heme was inhibited by bafilomycin A1 , concanamycin , NH4Cl , or chloroquine , consistent with the endosomal location of the transporter ; cellular uptake was uninhibited .
NTBI uptake was unaffected after removal of Tf receptors by Pronase or depletion of endogenous Tf .
Concentration dependence revealed that NTBI uptake into cells , cytosol , stroma , and the nonheme fraction had an apparent low affinity for iron ; heme incorporation behaved like a high - affinity process , as did an expression assay for DMT1 .
DMT1 serves in both apparent high - affinity NTBI membrane transport and the exit of iron from the endosome during Tf delivery of iron in rat reticulocytes ; the low - affinity membrane transporter , however , exhibits little dependence on DMT1 .
******************************* END ABSTRACT 9989781 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_915'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9989809 ***********************************************
Association with E2F-1 governs intracellular trafficking and polyubiquitination of DP-1 .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_935'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

While DP proteins stabilize DNA binding of E2F proteins , and influence the entry of E2F-4 and E2F-5 into the nucleus , the role of DP proteins in E2F - dependent gene expression is not well understood .
Using immunolocalization , immunoprecipitation , and cell fractionation experiments , here we show association with E2F subunits governs intracellular trafficking and ubiquitination of DP-1 .
In transient transfection experiments , DP-1 polypeptides that stably bound E2F-1 entered the nucleus .
DP-1 proteins that failed to associate with E2F subunits accumulated in the cell cytoplasm as polyubiquitinated DP-1 .
A Chinese hamster cell line that conditionally expresses HA - DP-1 was used to examine the effect of DP-1 on cell cycle progression .
In serum response experiments , moderate increases in HA - DP-1 led to a threefold increase in E2F DNA binding activity in vitro , a corresponding increase in dhfr gene expression during transition of G1 , and higher rates of S phase entry .
However , flow cytometry showed cells expressing very high levels of HA - DP-1 failed to enter the S phase .
Inhibition of cell cycle progression by high levels of HA - DP-1 was associated with the accumulation of other ubiquitinated cellular proteins , including c-jun and the cyclin - dependent kinase inhibitor p21 , indicating that degradation of ubiquitinated proteins is required for progression from G0 to S phase even in the presence of activated E2F .
Under similar conditions , expression of E2F-1 reduced the levels of ubiquitinated cellular proteins and accelerated cell cycle progression .
Our studies indicate association with E2F subunits prevents ubiquitin - dependent degradation of DP-1 in the cytoplasm by promoting nuclear entry of E2F / DP heterodimers .
******************************* END ABSTRACT 9989809 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_936'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9914793 ***********************************************
A new antitumor agent amrubicin induces cell growth inhibition by stabilizing topoisomerase II-DNA complex .
Amrubicin is a novel , completely synthetic 9-aminoanthracycline derivative .
Amrubicin and its C-13 alcohol metabolite , amrubicinol , inhibited purified human DNA topoisomerase II ( topo II ) .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_965'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

In this study , we found that ICRF-193 , a topo II catalytic inhibitor , antagonized both DNA - protein complex formation and double - strand DNA breaks induced by amrubicin and amrubicinol .
Coordinately , cell growth inhibition induced by amrubicin and amrubicinol , but not that induced by DXR , was antagonized by ICRF-193 .
Taken together , these findings indicate that the cell growth - inhibitory effects of amrubicin and amrubicinol are due to DNA - protein complex formation followed by double - strand DNA breaks , which are mediated by topo II .
******************************* END ABSTRACT 9914793 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_966'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9915795 ***********************************************
GATA - 6 activates transcription of thyroid transcription factor-1 .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_994'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

GATA - 5 and - 6 , members of the zinc finger family of transcription factors , are also expressed in various cell types within in the developing lung .
In the present work , GATA - 6 mRNA was detected in adult mouse lung , purified mouse type II epithelial cells , and differentiated mouse pulmonary adenocarcinoma cells ( MLE-15 cells ) , being co - expressed with TTF-1 mRNA .
In order to test whether GATA factors regulated TTF-1 gene transcription , GATA - 5 and - 6 expression vectors were co - transfected with TTF-1 luciferase expression vector .
GATA - 6 , but not GATA -5 , markedly activated TTF-1 gene transcription in HeLa cells .
EMSA and supershift analysis with GATA - 6 antiserum demonstrated that GATA - 6 in MLE-15 cell nuclear extracts bound to an element located 96-101 base pairs from major start of TTF-1 gene transcription .
Site directed mutagenesis of the GATA element in the TTF-1 promoter region inhibited transactivation by GATA - 6 in HeLa cells .
GATA - 6 is co - expressed with TTF-1 in the respiratory epithelium in vivo and respiratory epithelial cells in vitro .
GATA - 6 strongly enhanced activity of the human TTF-1 gene promoter in vitro .
These findings support the concept that GATA - 6 may play an important role in lung cell differentiation and gene expression , at least in part by altering the expression of TTF-1 and its potential targets .
******************************* END ABSTRACT 9915795 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_995'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9914383 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_1015'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

The antifungal and immunosuppressive drug rapamycin arrests the cell cycle in G1 -phase in both yeast and mammalian cells .
In mammalian cells , rapamycin selectively inhibits phosphorylation and activation of p70 S6 kinase ( p70(S6K ) ) , a protein involved in the translation of a subset of mRNAs , without affecting other known kinases .
We now report that rapamycin causes chromosome malsegregation in mammalian and yeast cells .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_1060'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

The number of ana-telophases with displaced chromosomes and interphase and mitotic cells with an irregular number of centrosomes was also determined in CHEF cells .
In quiescent mammalian cells ( human lymphocytes and CHEF cells ) induced with growth factor to re- enter the cell cycle , rapamycin was effective when cells were exposed at the time of p70(S6K ) activation .
In yeast , rapamycin was more effective when treatment was started in G1 - than in G2 - synchronized cells .
Cells from ataxia telangiectasia ( A- T ) patients are characterized by chromosome instability and have recently been found to be resistant to the growth - inhibiting effect of rapamycin .
We found that an A- T lymphoblastoid cell line was also resistant to the induction of chromosome malsegregation by rapamycin , but the level of spontaneous aneuploidy was higher than in normal cells .
In yeast , the induction of chromosome malsegregation was dependent on the presence of a wild - type TUB2 gene , encoding the beta-subunit of tubulin .
The finding that rapamycin acts in different cell types and organisms suggests that the drug affects a conserved step important for proper segregation of chromosomes .
One or more proteins required for chromosome segregation could be under the control of the rapamycin - sensitive pathway .
******************************* END ABSTRACT 9914383 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1061'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9916109 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_1080'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

We have studied the ability of the Norwegian group B meningococcal outer membrane vesicle ( OMV ) vaccine , when administered intranasally without adjuvant , to induce T-cell responses in humans .
A group of 12 vaccinees was immunized with four doses of OMVs ( 250 micrograms of protein / dose ) at weekly intervals , and a single booster dose was given 5 months later .
In vitro T-cell proliferation in response to the OMV vaccine , purified PorA ( class 1 ) protein , PorB ( class 3 ) protein , and one unrelated control antigen ( Mycobacterium bovis BCG ) was measured by [3H]thymidine incorporation into peripheral blood mononuclear cells obtained from the vaccinees before and after the immunizations .
The nasal OMV immunizations induced antigen - specific T-cell responses in the majority of the vaccinees when tested against OMVs ( 7 of 12 ) and the PorA antigen ( 11 of 12 ) .
None of the vaccinees showed a vaccine - induced T-cell response to the PorB antigen after the initial four doses .
Although some individuals responded to all the vaccine antigens after the booster dose , this response was not significant when the vaccinees were analyzed as a group .
We have also demonstrated that the PorA antigen - specific T-cell responses correlated with anti- OMV immunoglobulin A ( IgA ) levels in nasal secretions , with anti- OMV IgG levels in serum , and with serum bactericidal activity .
In conclusion , we have shown that it is possible to induce antigen - specific T-cell responses in humans by intranasal administration of a meningococcal OMV vaccine without adjuvant .
******************************* END ABSTRACT 9916109 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1081'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9973378 ***********************************************
IL-4 selectively inhibits IL-2-triggered Stat5 activation , but not proliferation , in human T cells .
IL-2 activates several distinct signaling pathways that are important for T cell activation , proliferation , and differentiation into both Th1 and Th2 phenotypes .

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_1106'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

As IL-2 synergizes with IL-12 in promoting Th1 differentiation , the effects of IL-4 on IL-2 signal transduction were investigated .
IL-4 suppressed activation of DNA binding and tyrosine phosphorylation of the transcription factor Stat5 by IL-2 , and suppressed the expression of the IL-2- inducible genes CD25 , CIS , the PGE2 receptor , and cytokine responsive ( CR ) genes CR1 and CR8 .
Activation of Stat5 by cytokines that share a common gamma receptor subunit , IL-2 , IL-7 , and IL-15 , was suppressed by preculture in IL-4.
Activation of the Jak1 and Jak3 kinases that are proximal to Stat5 in the IL-2- Jak-STAT signaling pathway was suppressed , and this correlated with inhibition of IL-2Rbeta subunit expression .
In contrast to suppression of Stat5 , proliferative responses to IL-2 were augmented in IL-4-cultured cells , and activation of proliferative pathways leading to activation of mitogen activated protein kinases , induction of expression of Myc , Fos , Pim-1 , and cyclin D3 , and decreased levels of the cyclin - dependent kinase inhibitor p27 were intact .
These results identify molecular mechanisms underlying interactions between IL-4 and IL-2 in T cells and demonstrate that one mechanism of regulation of IL-2 activity is selective and differential modulation of signaling pathways .
******************************* END ABSTRACT 9973378 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1107'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9917510 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

</xsl:when>

<xsl:when test="@id='word_1128'">

<xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
   <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
  <xsl:text> </xsl:text>

<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Protein kinase C ( PKC ) designates a family of kinases that regulate many essential functions including cell growth and differentiation .
The tight regulation of PKC activity is crucial for maintaining normal cellular proliferation and excessive activity leads to abnormal or uncontrolled cell growth .
Recent reports indicate that malignant glioma cell lines express 100 to 1000 - fold higher PKC activity when compared to non - neoplastic astrocytes .
This high activity correlates well with the proliferation of tumor cells in vitro .
We recently reported on the anti - proliferative properties of selective PKC inhibitors on the growth of U-373MG human astrocytoma cell line , and their ability to block mitogen - activated protein ( MAP ) kinase pathway activated by substance P ( SP ) neuropeptide receptor signaling via a PKC- dependent mechanism .
Therefore , inhibiting PKC activity by selective PKC inhibitors may present a promising approach for improving astroglial brain tumor therapy .
For this purpose , we constructed a high throughput model cell system to evaluate the efficacy of PKC inhibitors .
This system is based on the measurement of light production in U-373MG cells stably transfected with the luciferase reporter gene whose expression depends on the transcriptional activation of GAL4-Elk1 fusion protein by enzyme components of the MAP kinase pathway and the upstream activation of PKC ( PKC activation --&gt; MAP kinases --&gt; GAL4-Elk1 phosphorylation --&gt; luciferase expression --&gt; luciferase activity ) .
In brief , we have demonstrated that the PKC activator 12-O-tetradecanoyl phorbol 13-acetate ( TPA ) - induced luciferase activity in this cell system is mediated via the MAP kinase pathway and can be blocked in the presence of MEK1 selective inhibitors ( PD 098059 or U0126 ) .
We also demonstrated that TPA - induced luciferase activity in U-373MG stable clones can be blocked by PKC inhibitors ( CGP 41251 , Go 6976 , and GF 109203X ) in a concentration dependent manner .
In contrast , epidermal growth factor ( EGF ) - induced luciferase activity , which is independent of PKC activation ( Ras --&gt; Raf-1--&gt; MEK1 --&gt; MAP kinases --&gt; GAL4-Elk1 phosphorylation --&gt; luciferase expression --&gt; luciferase activity ) can only be blocked using a selective EGF receptor inhibitor ( AG 1478 ) .
In conclusion , we have constructed a model cell system for the high throughput screening and identification of PKC inhibitors potentially active against astrocytoma cells in culture .
******************************* END ABSTRACT 9917510 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>


<xsl:otherwise>
 <xsl:value-of select="mmax:registerDiscourseElement(@id)"/>
  <xsl:apply-templates select="mmax:getStartedMarkables(@id)" mode="opening"/>
  <xsl:value-of select="mmax:setDiscourseElementStart()"/>
 <xsl:apply-templates/>
  <xsl:value-of select="mmax:setDiscourseElementEnd()"/>
  <xsl:apply-templates select="mmax:getEndedMarkables(@id)" mode="closing"/>
<xsl:text> </xsl:text>
</xsl:otherwise>

</xsl:choose>
</xsl:template>

<xsl:template match="celltype:markable" mode="opening">
<xsl:value-of select="mmax:startBold()"/>
<xsl:value-of select="mmax:addLeftMarkableHandle(@mmax_level, @id, '[')"/>
<xsl:value-of select="mmax:endBold()"/>
</xsl:template>

<xsl:template match="celltype:markable" mode="closing">
<xsl:value-of select="mmax:startBold()"/>
<xsl:value-of select="mmax:addRightMarkableHandle(@mmax_level, @id, ']')"/>
<xsl:value-of select="mmax:endBold()"/>
</xsl:template>
</xsl:stylesheet>
                
                
                   

