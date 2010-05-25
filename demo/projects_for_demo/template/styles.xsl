<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
xmlns:mmax="org.eml.MMAX2.discourse.MMAX2DiscourseLoader"
xmlns:cell_component="www.eml.org/NameSpaces/cell_component">
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
***************************** BEGIN ABSTRACT 11967159 ***********************************************

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

<xsl:when test="@id='word_19'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Faithful duplication of the genetic material requires that replication origins fire only once per cell cycle .
Central to this control is the tightly regulated formation of prereplicative complexes ( preRCs ) at future origins of DNA replication .
In all eukaryotes studied , this entails loading by Cdc6 of the Mcm2 - 7 helicase next to the origin recognition complex ( ORC ) .
More recently , another factor , named Cdt1 , was shown to be essential for Mcm loading in fission yeast and Xenopus as well as for DNA replication in Drosophila and humans .
Surprisingly , no Cdt1 homolog was found in budding yeast , despite the conserved nature of origin licensing .
Here we identify Tah11 / Sid2 , previously isolated through interactions with topoisomerase and Cdk inhibitor mutants , as an ortholog of Cdt1 .
We show that sid2 mutants lose minichromosomes in an ARS number - dependent manner , consistent with ScCdt1 / Sid2 being involved in origin licensing .
Accordingly , cells partially depleted of Cdt1 replicate DNA from fewer origins , whereas fully depleted cells fail to load Mcm2 on chromatin and fail to initiate but not elongate DNA synthesis .

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

<xsl:when test="@id='word_37'">

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

******************************* END ABSTRACT 11967159 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_38'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10027981 ***********************************************
Autogenous regulation of transcription termination factor Rho and the requirement for Nus factors in Bacillus subtilis .

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

<xsl:when test="@id='word_63'">
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

<xsl:when test="@id='word_105'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

The B. subtilis rho gene was autoregulated at the level of transcription ; autoregulation required sequences within the rho mRNA leader region and gene .

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

<xsl:when test="@id='word_128'">

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

Rho was not involved in bulk mRNA decay in B. subtilis .
The E. coli elongation factors NusA and NusG target Rho , and the importance of these proteins in B. subtilis was examined by gene disruption .
The B. subtilis NusG was inessential for both the viability and the autoregulation of Rho , whereas NusA was essential , and the requirement for NusA was independent of Rho .
This contrasts with E. coli in which NusG is essential but NusA becomes dispensable if Rho terminates transcription less efficiently .
******************************* END ABSTRACT 10027981 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_129'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10982444 ***********************************************
Post - transcriptional maturation of the S receptor kinase of Brassica correlates with co - expression of the S-locus glycoprotein in the stigmas of two Brassica strains and in transgenic tobacco plants .

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

<xsl:when test="@id='word_203'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

We found that mutant stigmas synthesized wild - type levels of SRK transcripts but failed to produce SRK protein at any of the developmental stages analyzed .
Furthermore , SRK was shown to form aberrant high - molecular mass aggregates when expressed alone in transgenic tobacco ( Nicotiana tabacum ) plants .

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

<xsl:when test="@id='word_243'">

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

In analyses of protein extracts under reducing and non - reducing conditions , evidence of intermolecular association was obtained only for SLG , a fraction of which formed disulfide - linked oligomers and was membrane associated .
The data indicate that , at least in plants carrying the S haplotypes we analyzed , SRK is an inherently unstable protein and that SLG facilitates its accumulation to physiologically relevant levels in Brassica stigmas .
******************************* END ABSTRACT 10982444 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_244'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11788965 ***********************************************
Phylogenetic footprinting reveals multiple regulatory elements involved in control of the meiotic recombination gene , REC102 .
REC102 is a meiosis - specific early exchange gene absolutely required for meiotic recombination in Saccharomyces cerevisiae .
Sequence analysis of REC102 indicates that there are multiple potential regulatory elements in its promoter region , and a possible regulatory element in the coding region .
This suggests that the regulation of REC102 may be complex and may include elements not yet reported in other meiotic genes .
To identify potential cis - regulatory elements , phylogenetic footprinting analysis was used .

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

<xsl:when test="@id='word_264'">

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

Deletion analysis demonstrated that the early meiotic gene regulatory element URS1 was necessary but not sufficient for proper regulation of REC102 .
Upstream elements , including the binding sites for Gcr1p , Yap1p , Rap1p and several novel conserved sequences , are also required for the normal regulation of REC102 as well as a Rap1p binding site located in the coding region .
The data in this paper support the use of phylogenetic comparisions as a method for determining important sequences in complex promoters .
******************************* END ABSTRACT 11788965 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_265'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 14962816 ***********************************************
Genetic approaches to assessing evidence for a T helper type 1 cytokine defect in adult asthma .
Recent evidence suggests that deficiency in the Th1 cytokine pathway may underlie the susceptibility to allergic asthma .
This study examined whether (1) single - nucleotide polymorphisms exist in the promoter region of the two interleukin ( IL ) - 12 subunit genes in patients with asthma ; (2) messenger RNA and protein expressions of signal transducers and activators of transcription , IL-12 , IFN-gamma , and their receptors are altered in asthma ; and (3) linkage to genes in the Th1 pathway is present in families with asthma in Iceland .

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

<xsl:when test="@id='word_287'">
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

<xsl:when test="@id='word_307'">
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

<xsl:when test="@id='word_348'">

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

Moreover , we found no differences in the messenger RNA or protein expression signals of genes in the IL-12 pathway between the patients and control subjects .
We conclude that decrease in Th1 type cytokine response is unlikely to present a primary event in asthma .
******************************* END ABSTRACT 14962816 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_349'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 12017543 ***********************************************
Steroidogenic factor 1 : an essential mediator of endocrine development .
The orphan nuclear receptor steroidogenic factor 1 ( SF-1 , also called Ad4BP and officially designated NR5A1 ) has emerged as an essential regulator of endocrine development and function .
Initially identified as a tissue - specific transcriptional regulator of the cytochrome P450 steroid hydroxylases , SF-1 has considerably broader roles , as evidenced from studies in knockout mice lacking SF-1 .
The SF-1 - knockout mice lacked adrenal glands and gonads and therefore died from adrenal insufficiency within the first week after birth .

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

<xsl:when test="@id='word_389'">

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

These studies delineated essential roles of SF-I in regulating endocrine differentiation and function at multiple levels , particularly with respect to reproduction .
This chapter will review the experiments that established SF-1 as a pivotal , global determinant of endocrine differentiation and function .
We next discuss recent insights into the mechanisms controlling the expression and function of SF-1 as well as the current status of research aimed at delineating its roles in specific tissues .
Finally , we highlight areas where additional studies are needed to expand our understanding of SF-1 action .
******************************* END ABSTRACT 12017543 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_390'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10803549 ***********************************************
New methods to use fish cytochrome P4501A to assess marine organic pollutants .

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

<xsl:when test="@id='word_426'">

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

Degenerate oligos were used to amplify by reverse transcription and PCR ( RT - PCR ) specific cyp1A cDNA sequences , used subsequently to design specific primers to get the full cDNA by rapid amplification of cDNA ends .
A new assay has been developed to quantitate cyp1A expression by RT - PCR in an automated DNA sequencer .
The effect of beta-naphthoflavone inducing biotransformation has been used to compare three distinct pollution biomarkers : EROD activity , ELISA determination of CYP1A , and 2-aminoanthracene ( 2-AA ) activation .
Immunodetection by ELISA or Western blot was inconsistent in S. aurata and L. aurata .
EROD activity yielded satisfactory results ; the higher induction was observed by bioactivation of 2-AA to mutagens detected with strain BA149 of Salmonella typhimurium , in agreement with the high sensitivity previously described for this biomarker .
The present paper summarizes the current status of our research .
******************************* END ABSTRACT 10803549 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_427'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 1358753 ***********************************************
Comparative studies of Drosophila Antennapedia genes .
The Antennapedia ( Antp ) homeotic gene of Drosophila melanogaster controls cell fates and pattern formation in the epidermis , nervous system and mesoderm of thoracic segments .
Its expression is controlled at the levels of transcription , alternative RNA splicing , polyadenylation and translation .
Two nested Antp transcription units extend over 103 kb and produce sixteen different transcripts .

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

<xsl:when test="@id='word_459'">

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

The overall gene structures are similar .
There are many conserved sequence blocks throughout the large introns , at least 15 kb upstream of the first promoter , and at least 3 kb downstream of the last polyadenylation site .
Intron and exon sequence conservation around alternative splice sites indicates that alternative protein coding forms may also be conserved .
Protein coding potential is perfectly conserved around the C - terminal homeodomain , well conserved in the N - terminal region , and more variable in the middle .
The large size of the Antp gene may reflect a large number of control elements necessary for appropriate Antp protein expression .
The conservation of transcript complexity suggests functional requirements for the different protein forms .
******************************* END ABSTRACT 1358753 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_460'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10027972 ***********************************************

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

<xsl:when test="@id='word_488'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Escherichia coli K-12 harbours a chromosomal gene , clyA ( sheA , hlyE ) , that encodes a haemolytic 34 kDa protein .

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

<xsl:when test="@id='word_514'">

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

The secretion of ClyA was confined to the log phase and paralleled by the partial release of several other periplasmic proteins .
Sequencing of ClyA revealed the translational start point of the clyA gene and demonstrated that the clyA gene product is not N-terminally processed during transport .
The transcription of clyA from its native promoter region was positively controlled by SlyA , a regulatory protein found in E. coli , Salmonella typhimurium and other Enterobacteriaceae .
SlyA - controlled transcription started predominantly 72 bp upstream from clyA , as shown by primer extension .
The corresponding putative promoter contains an unusual - 10 sequence ( TATGAAT ) that is separated from a conventional - 35 sequence by a GC - rich spacer .
Site - directed deletion of the G in the - 10 sequence abrogated the SlyA requirement for strong ClyA production , whereas a reduction in the G + C content of the spacer diminished the capability of SlyA to activate the clyA expression .
Osmotic protection assays and lipid bilayer experiments suggested that ClyA forms stable , moderately cation - selective transmembrane pores that have a diameter of about 2.5 - 3 nm .
******************************* END ABSTRACT 10027972 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_515'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 12228259 ***********************************************
The alternative sigma factor sigma ( E ) plays an important role in intestinal survival and virulence in Vibrio cholerae .
The alternative sigma factor sigma ( E ) ( RpoE ) is involved in the response to extracytoplasmic stress and plays a role in the virulence of a variety of different bacteria .

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

<xsl:when test="@id='word_538'">

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

The results here show that sigma(E) contributes significantly to the virulence of V. cholerae .
The DeltarpoE mutant was highly attenuated with a 50 % lethal dose more than 3 logs higher than that for the parental strain , and its ability to colonize the intestine was reduced approximately 30 - fold .
A time course of infection revealed that the number of CFU of the DeltarpoE mutant was approximately 1 log lower than that of the parental strain by 12 h postinoculation and decreased further by 24 h .
The defect in virulence in the DeltarpoE mutant thus appears to be a diminished ability to survive within the intestinal environment .
The results here also show that sigma(E) is not required for growth and survival of V. cholerae in vitro at high temperatures but is required under other stressful conditions , such as in the presence of 3 % ethanol .
As in Escherichia coli , the expression of rpoE in V. cholerae is dependent upon two promoters located upstream of the gene , P1 and P2 .
P1 appears to be sigma ( 70 ) dependent , whereas the downstream promoter , P2 , is positively autoregulated by sigma(E) .
******************************* END ABSTRACT 12228259 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_539'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10191090 ***********************************************
Functional characterization of the human PAX3 gene regulatory region .
Spatiotemporal expression of the PAX3 gene is tightly regulated during development .
We have isolated and sequenced the 5' - flanking regulatory region of human PAX3 .
Primer extension and ribonuclease protection mapping revealed that transcription is initiated from a single start site downstream of a TATA - like motif in human brain and peripheral tissues .
Functional dissection of the gene 's 5' - flanking region , which had been fused to a luciferase reporter gene and transiently expressed in rhabdomyosarcoma ( RD ) and cos-7 cells , indicated that the upstream region of PAX3 contains multiple positive and negative cis - acting regulatory elements .
While the basal promoter is likely to be driven by two CCAAT boxes located at nucleotide positions - 90 and - 135 , a cluster of regulatory elements acting as a strong repressor was detected between nucleotides - 1200 and - 650 .
Comparison of human and murine sequences revealed more than 90 % identity in this segment .
A polymorphic ( CA ) n repeat sequence and a G / C substitution are located 337 bp and 328 bp upstream of the transcription start site , respectively .

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

<xsl:when test="@id='word_569'">

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

Although the ( CA ) 25 variant of this PAX3 gene - linked polymorphic region ( PAX3LPR ) conferred lower transcriptional efficiency on the PAX3 promoter , a regulatory impact of the PAX3LPR on PAX3 expression related to brain plasticity and function remains to be demonstrated .
******************************* END ABSTRACT 10191090 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_570'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 8288531 ***********************************************
Mutation of flgM attenuates virulence of Salmonella typhimurium , and mutation of fliA represses the attenuated phenotype .

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

<xsl:when test="@id='word_590'">

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

Strain ST39 is nonmotile , carries an indeterminate deletion in and near the flgB operon , and is defective in the mviS ( mouse virulence Salmonella ) locus .
In flagellum - defective strains , the flgM gene product of S. typhimurium negatively regulates flagellar genes by inhibiting the activity of FliA , the flagellin - specific sigma factor .
In this study , flgM of wild - type S. typhimurium LT2 was found to complement the mviS defect in ST39 for virulence in mice and for enhanced survival in macrophages .
Transduction of flgM : : Tn10dCm into the parent strain SL3201 resulted in attenuation of mouse virulence and decreased survival in macrophages .
However , a flgM-fliA double mutant was fully virulent in mice and survived in macrophages at wild - type levels .
Thus , the absolute level of FliA activity appears to affect the virulence of S. typhimurium SL3201 in mice .
DNA hybridization studies showed that flgM - related sequences were present in species other than Salmonella typhimurium and that sequences related to that of fliA were common among members of the family Enterobacteriaceae .
Our results demonstrate that flgM and fliA , two genes previously shown to regulate flagellar operons , are also involved in the regulation of expression of virulence of S. typhimurium and that this system may not be unique to the genus Salmonella .
******************************* END ABSTRACT 8288531 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_591'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10027980 ***********************************************
The DNA binding protein Tfx from Methanobacterium thermoautotrophicum : structure , DNA binding properties and transcriptional regulation .

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

<xsl:when test="@id='word_621'">

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

The 16.1 kDa protein has an N - terminal basic domain with a helix - turn - helix motif for DNA binding and a C - terminal acidic domain possibly for transcriptional activation .
We report here on the DNA binding properties of the Tfx protein heterologously overproduced in Escherichia coli .
Tfx was found to bind specifically to a DNA sequence downstream of the promoter of the fmdECB operon , as shown by electrophoretic mobility shift assays and DNase I footprint analysis .
Northern blot hybridizations revealed that transcription of tfx is repressed during the growth of M. thermoautotrophicum in the presence of tung - state .
Based on its structure and properties , the DNA binding protein Tfx is proposed to be a transcriptional regulator composed of a basic DNA binding domain and an acidic activation domain .
******************************* END ABSTRACT 10027980 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_622'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 12023900 ***********************************************
Activation of mouse Pi - class glutathione S-transferase gene by Nrf2 ( NF-E2 - related factor 2) and androgen .
The Pi - class glutathione S-transferases ( GSTs ) play pivotal roles in the detoxification of xenobiotics , carcinogenesis and drug resistance .
The mechanisms of regulation of these genes during drug induction and carcinogenesis are yet to be elucidated .
Recently , Nrf2 ( NF-E2 - related factor 2 ; a bZip - type transcription factor ) knockout mice were shown to display impaired induction of Pi - class GST genes by drugs .

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

<xsl:when test="@id='word_647'">

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

To determine whether Nrf2 and the androgen receptor regulate GST-P1 directly , we analysed the molecular mechanism of activation of this gene by these factors .
The promoter of the GST-P1 gene was activated markedly by Nrf2 in transient transfection analyses .
Gel mobility shift assay and footprinting analyses revealed three Nrf2 binding sites : one at the proximal and two at distal elements , located at positions - 59 , - 915 and - 937 from the cap site .
The fifth intron of the GST-P1 gene contains the androgen - responsive region .
Multiple androgen receptor binding sites are clustered within a 500 bp region of this intron .
The whole fragment contains a minimum of seven androgen receptor binding sites , which collectively display strong androgen - dependent enhancer activity .
However , on division into small fragments containing two or three elements each , individual enhancer activities were dramatically decreased .
This suggests that multiple elements work synergistically as a strong androgen - responsive enhancer .
Our findings indicate that Nrf2 and the androgen receptor directly bind to and activate the mouse GST-P1 gene .
******************************* END ABSTRACT 12023900 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_648'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10556313 ***********************************************
Concerted regulation and molecular evolution of the duplicated SNRPB ' / B and SNRPN loci .
The human small nuclear ribonucleoprotein SNRPB ' / B gene is alternatively spliced to produce the SmB or SmB ' spliceosomal core proteins .
An ancestral duplication gave rise to the closely related SNRPN paralog whose protein product , SmN , replaces SmB ' / B in brain .
However , the precise evolutionary and functional relationship between these loci has not been clear .

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

<xsl:when test="@id='word_685'">
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

<xsl:when test="@id='word_718'">

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

The human SNRPB ' / B locus is biallelically unmethylated , unlike the imprinted SNRPN locus which is unmethyl - ated only on the expressed paternal allele .
Western analysis demonstrates that a compensatory feedback loop dramatically upregulates SmB ' / B levels in response to the loss of SmN in Prader - Willi syndrome brain tissue , potentially reducing the phenotypic severity of this syndrome .
These findings imply that these two genes encoding small nuclear ribonucleoprotein components are subject to dosage compensation .
Therefore , a more global regulatory network may govern the maintenance of stoichiometric levels of spliceosomal components and may constrain their evolution .
******************************* END ABSTRACT 10556313 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_719'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10949371 ***********************************************
Electron transport controls transcription of the thioredoxin gene ( trxA ) in the cyanobacterium Synechocystis sp. PCC 6803 .

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

<xsl:when test="@id='word_747'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Transcript accumulation is similar in all standard growth conditions but strongly decreases after transferring cell cultures from light to darkness .
In steady - state conditions , trxA transcription is reduced at high ( 150 - 500 microE m(-2) s(-1) ) compared with moderate ( 10 - 50 microE m(-2) s(-1) ) light intensities .
The stability of the trxA transcript was similar at different light intensities , and also in darkness .
Photosynthetic electron transport inhibitors , as well as glucose starvation in a mutant strain lacking photosystem II , promote a strong decline in the level of trxA transcript .

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

<xsl:when test="@id='word_772'">

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

Unlike the trxA mRNA , the amount of thioredoxin protein was not reduced in the dark , neither at high light intensities , indicating that thioredoxin protein is very stable .
Our results indicate that the thioredoxin encoded by the trxA gene is likely to be primarily regulated at the transcriptional level , rather than at the protein level , by the electron transport generated photosynthetically or from glucose metabolism .
******************************* END ABSTRACT 10949371 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_773'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10929938 ***********************************************
Acropetal disappearance of PsAD1 protein in pea axillary buds after the release of apical dominance .

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

<xsl:when test="@id='word_815'">

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

To further elucidate the function of PsAD1 , we investigated the temporal and spatial distribution patterns of PsAD1 protein using Western blot and immunocytochemical analyses .
Western blot analyses showed that accumulation patterns of PsAD1 protein in axillary buds after decapitation and in response to IAA and 6-benzyladenine were the same as those of PsAD1 mRNA .
Immunocytochemical analyses showed that ( 1 ) PsAD1 proteins were localized in the procambia , leaf primordia , apical meristem , and secondary axillary buds in the dormant axillary bud , and this distribution was the same as that of PsAD1 mRNA , (2) PsAD1 proteins acropetally disappeared after decapitation , and (3) the growth of axillary buds occurred in the same manner .
These acropetal changes occur in a manner similar to the way in which the procambium differentiates into vascular tissue .
These results suggest that PsAD1 plays some role in the inhibition of growth and differentiation , or in the maintenance of the dormant state in axillary buds .
******************************* END ABSTRACT 10929938 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_816'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11929523 ***********************************************

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

<xsl:when test="@id='word_845'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

We showed previously that transcription of the ran gene in Giardia lamblia is regulated by an AT - rich initiator .
In the present study , the ran initiator was found to regulate transcription of a neighbouring PHD zinc - finger protein gene .
Deletion and scanning mutagenesis of the phd promoter in a firefly luciferase reporter system showed that the promoter activity is determined by multiple single - stranded T - tract DNA elements distributed into a distal domain spanning the ran initiator ( - 134 / - 103 ) and a proximal domain ( - 88 / - 48 ) spanning phd messenger RNA ( mRNA ) start sites ( - 74 , - 55 and - 53 relative to the first ATG ) .

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

<xsl:when test="@id='word_882'">

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

The T - tract elements in the phd and ran initiators compete for similar ssDNA binding proteins .
Mutation of - 47 / - 42 resulted in dramatic reduction of luciferase activity without changing luciferase mRNA levels , indicating the potential involvement of a regulatory mechanism in PHD protein translation .
These findings suggest that G. lamblia uses multiple copies of a T - tract element as both core and distal elements in regulating transcription initiation , and that expression of the phd gene is regulated at multiple levels .
******************************* END ABSTRACT 11929523 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_883'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11844762 ***********************************************
Trinucleotide GAA repeats dictate pMGA gene expression in Mycoplasma gallisepticum by affecting spacing between flanking regions .

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

<xsl:when test="@id='word_904'">

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

A trinucleotide GAA repeat region is located upstream of the pMGA transcription start site .
The length of the repeat region varies at a high frequency due to changes in the number of repeat units .
Previous studies have shown that pMGA genes are transcribed when 12 GAA repeats are present but are not transcribed when the number of repeats is not 12 .
To further analyze the mechanism of gene regulation , the pMGA promoter region was modified either by deleting the nucleotides 5 &quot; of the GAA repeats or by inserting linkers of 10 or 12 bp at a position 3 &quot; of the repeats .
The modified promoter region was fused to a promoterless lacZ gene and transformed into M. gallisepticum by using transposon Tn4001 as a vector .
Transformants and successive generations of progeny were analyzed with 5-bromo-4-chloro-3-indolyl-beta-D-galactopyranoside ( X-Gal ) to monitor beta-galactosidase activity .
For the transformants of M. gallisepticum containing the reporter with deletion of nucleotides 5 &quot; of the GAA repeats , GAA - dependent pMGA gene regulation was abolished .
For the transformants containing the reporter with an addition of 10 - or 12 - bp linkers , lacZ was expressed only when eight GAA repeats were present .
These data indicate that the nucleotides 5 &quot; of the GAA repeats as well as the spacing between the GAA repeats and sequences downstream ( 3 &quot; ) of the repeats are important for pMGA gene expression .
******************************* END ABSTRACT 11844762 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_905'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11032021 ***********************************************
Caspase - dependent apoptosis by ectopic expression of E2F-4 .
E2F is a family of transcription factors which regulates cell cycle and apoptosis of mammalian cells .
E2F-1-3 localize in the nucleus , and preferentially bind pRb , while E2F-4 and 5 have no nuclear localization signal and preferentially bind p107 / p130 .
E2F-6 suppresses the transcriptional activity of other E2F proteins .
DP-1 and 2 are heterodimeric partners of each E2F protein .

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

<xsl:when test="@id='word_936'">

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

We found that E2F-4 , as well as DP-1 and E2F-1 , induced growth arrest and caspase - dependent apoptosis .
E2F-4 did not have a marked effect on cell cycle progression , while E2F-1 induced DNA synthesis of resting cells and DP-1 arrested cells in G1 .
Ectopic expression of E2F-4 did not activate E2F - dependent transcription .
Our results suggest that expression of E2F-4 at elevated levels induces growth arrest and apoptosis of mammalian cells through a mechanism distinct from E2F-1 and DP-1 .
******************************* END ABSTRACT 11032021 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_937'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11830669 ***********************************************
The Brucella suis virB operon is induced intracellularly in macrophages .

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

Reverse transcriptase - PCR showed that the 12 genes encoding the Brucella suis VirB system form an operon .
Semiquantitative measurements of virB mRNA levels by slot blotting showed that transcription of the virB operon , but not the flanking genes , is regulated by environmental factors in vitro .
Flow cytometry used to measure green fluorescent protein expression from the virB promoter confirmed the data from slot blots .
Fluorescence - activated cell sorter analysis and fluorescence microscopy showed that the virB promoter is induced in macrophages within 3 h after infection .
Induction only occurred once the bacteria were inside the cells , and phagosome acidification was shown to be the major signal inducing intracellular expression .
Because phagosome acidification is essential for the intracellular multiplication of Brucella , we suggest that it is the signal that triggers the secretion of unknown effector molecules .
These effector molecules play a role in the remodeling of the phagosome to create the unique intracellular compartment in which Brucella replicates .
******************************* END ABSTRACT 11830669 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_966'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 14506398 ***********************************************
Evaluation of aldehyde dehydrogenase 1 promoter polymorphisms identified in human populations .
BACKGROUND :
Cytosolic aldehyde dehydrogenase , or ALDH1A1 , functions in ethanol detoxification , metabolism of neurotransmitters , and synthesis of retinoic acid .
Because the promoter region of a gene can influence gene expression , the ALDH1A1 promoter regions were studied to identify polymorphism , to assess their functional significance , and to determine whether they were associated with a risk for developing alcoholism .
METHODS :

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

<xsl:when test="@id='word_985'">
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

<xsl:when test="@id='word_1020'">

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

The functional significance of each polymorphism was determined through in vitro expression analysis by using HeLa and HepG2 cells .
RESULTS :
Two polymorphisms , a 17 base pair ( bp ) deletion ( - 416 / - 432 ) and a 3 bp insertion (-524) , were discovered in the ALDH1A1 promoter region : ALDH1A1*2 and ALDH1A1*3 , respectively .
ALDH1A1*2 was observed at frequencies of 0.035 , 0.023 , 0.023 , and 0.012 in the Asian , Caucasian , Jewish , and African American populations , respectively .
ALDH1A1*3 was observed only in the African American population , at a frequency of 0.029 .
By using HeLa and HepG2 cells for in vitro expression , the activity of the luciferase reporter gene was significantly decreased after transient transfection of ALDH1A1*3 - luciferase compared with the wild - type construct ALDH1A1*1 - luciferase .
In an African American population , a trend for higher frequencies of the ALDH1A1*2 and ALDH1A1*3 alleles was observed in a population of alcoholics ( p = 0.03 and f = 0.12 , respectively ) compared with the control population .
CONCLUSIONS :
ALDH1A1*2 and ALDH1A1*3 may influence ALDH1A1 gene expression .
Both ALDH1A1*2 and ALDH1A1*3 produce a trend in an African American population that may be indicative of an association with alcoholism ; however , more samples are required to validate this observation .
The underlying mechanisms contributing to these trends are still unknown .
******************************* END ABSTRACT 14506398 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1021'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10929944 ***********************************************
The ethylene - regulated expression of CS-ETR2 and CS-ERS genes in cucumber plants and their possible involvement with sex expression in flowers .
It has been reported that ethylene production by cucumber plants is strongly related to the sex expression of their flowers .
It has also been shown that both CS-ACS2 gene expression and ethylene evolution are much greater in gynoecious cucumber plants than monoecious ones .

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

<xsl:when test="@id='word_1061'">

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

Of these three genes , CS-ETR2 and CS-ERS mRNA accumulated more substantially in the shoot apices of the gynoecious cucumber than those of the monoecious one .
Their expression patterns correlated with the expression of the CS-ACS2 gene and with ethylene evolution in the shoot apices of the two types of cucumber plants .
Accumulation of CS-ETR2 and CS-ERS mRNA was significantly elevated by the application of Ethrel , an ethylene - releasing agent , to the shoot apices of monoecious cucumber plants .
In contrast , the accumulation of their transcripts was lowered when aminoethoxyvinyl glycine ( AVG ) , an inhibitor of ethylene biosynthesis , was applied to the shoot apices of gynoecious cucumber plants .
Thus , the expression of CS-ETR2 and CS-ERS is , at least in part , regulated by ethylene .
The greater accumulation of CS-ETR2 and CS-ERS mRNA in gynoecious cucumber plants may be due to the higher level of endogenous ethylene , which plays a role in the development of female flowers .
******************************* END ABSTRACT 10929944 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1062'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10028182 ***********************************************

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

<xsl:when test="@id='word_1073'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

The ability of a microorganism to adhere to a solid support and to initiate a colony is often the first stage of microbial infections .

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

<xsl:when test="@id='word_1097'">

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

Colony formation has not been studied before probably because this species is not pathogenic .
However , S. cerevisiae can be a convenient model to study this process , thanks to well - developed genetics and the full knowledge of its nucleotide sequence .
A preliminary characterization of the recently cloned essential IRR1 gene indicated that it may participate in cell - cell / substrate interactions .
Here we show that lowering the level of expression of IRR1 ( after fusion with a regulatory catalase A gene promoter ) affects colony formation and disturbs zygote formation and spore germination .
All these processes involve cell - cell or cell - solid support contacts .
The IRR1 protein is localized in the cytosol as verified by immunofluorescence microscopy , and confirmed by cell fractionation and Western blotting .
This indicates that Irr1p is not directly involved in the cell - solid support adhesion , but may be an element of a communication pathway between the cell and its surroundings .
******************************* END ABSTRACT 10028182 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1098'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10021331 ***********************************************
Separation of shoot and floral identity in Arabidopsis .
The overall morphology of an Arabidopsis plant depends on the behaviour of its meristems .

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

<xsl:when test="@id='word_1111'">

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

The distinction between these alternative fates requires separation between the function of floral meristem identity genes and the function of an antagonistic group of genes , which includes TERMINAL FLOWER 1 .
We show that the activities of these genes are restricted to separate domains of the shoot apex by different mechanisms .
Meristem identity genes , such as LEAFY , APETALA 1 and CAULIFLOWER , prevent TERMINAL FLOWER 1 transcription in floral meristems on the apex periphery .
TERMINAL FLOWER 1 , in turn , can inhibit the activity of meristem identity genes at the centre of the shoot apex in two ways ; first by delaying their upregulation , and second , by preventing the meristem from responding to LEAFY or APETALA 1 .
We suggest that the wild - type pattern of TERMINAL FLOWER 1 and floral meristem identity gene expression depends on the relative timing of their upregulation .
******************************* END ABSTRACT 10021331 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1112'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11944914 ***********************************************
Transgenic expression of the EXT2 gene in developing chondrocytes enhances the synthesis of heparan sulfate and bone formation in mice .
Hereditary multiple exostoses ( HME ) , a dominantly inherited disorder characterized by multiple cartilaginous tumors , is caused by mutations in the gene for , EXT1 or EXT2 .
Recent studies have revealed that EXT1 and EXT2 are required for the biosynthesis of heparan sulfate and exert maximal transferase activity as a complex .

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

<xsl:when test="@id='word_1146'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

In this study , to investigate the biological role of EXT2 in bone development in vivo and the pathological role of HME mutations in the development of exostoses , we generated transgenic mice expressing EXT2 or mutant EXT2 in developing chondrocytes .

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

<xsl:when test="@id='word_1181'">

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

The expression of EXT1 is concomitantly upregulated in EXT2 - transgenic and even mutant EXT2 - transgenic mice , suggesting an interactive regulation of EXT1 and EXT2 expression .
These findings support that the EXT2 gene encodes an essential component of the glycosyltransferase complex required for the biosynthesis of heparan sulfate , which may eventually modulate the signaling involved in bone formation .
******************************* END ABSTRACT 11944914 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1182'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10200305 ***********************************************
Polymorphism in RANTES chemokine promoter affects HIV-1 disease progression .
RANTES ( regulated on activation normal T cell expressed and secreted ) is one of the natural ligands for the chemokine receptor CCR5 and potently suppresses in vitro replication of the R5 strains of HIV-1 , which use CCR5 as a coreceptor .
Previous studies showed that peripheral blood mononuclear cells or CD4 ( + ) lymphocytes obtained from different individuals had wide variations in their ability to secrete RANTES .
These findings prompted us to analyze the upstream noncoding region of the RANTES gene , which contains cis - acting elements involved in RANTES promoter activity , in 272 HIV-1 - infected and 193 non - HIV-1-infected individuals in Japan .
Our results showed that there were two polymorphic positions , one of which was associated with reduced CD4 ( + ) lymphocyte depletion rates during untreated periods in HIV-1 - infected individuals .

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

<xsl:when test="@id='word_1216'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Functional analyses of RANTES promoter activity indicated that the RANTES-28G mutation increases transcription of the RANTES gene .

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

<xsl:when test="@id='word_1243'">

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

******************************* END ABSTRACT 10200305 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1244'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10900827 ***********************************************
A nucleotide deletion causing a translational stop in the protease reading frame of bovine leukaemia virus ( BLV ) results in modified protein expression and loss of infectivity .

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

<xsl:when test="@id='word_1268'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

The comparison of the previously published BLV provirus sequence from Belgium , Australia and Japan showed that the protease gene ( prt ) of the Australian and the Japanese isolate contain a nucleotide deletion when compared to the Belgian isolate .

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

<xsl:when test="@id='word_1310'">

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

The only variations between these sequences and the Belgian isolate consist of nucleotide substitutions .
The delection of one nucleotide of the prt gene of the Japanese and the Australian BLV tumour isolate caused a changed reading frame and a premature translational stop .
It was shown that the Japanese provirus is non - infectious in transfected cell culture and in injected sheep .
To analyse the impact of the prt mutation on viral protein expression and infectivity , the prt region of the Japanese provirus was exchanged with the prt region from the Belgian provirus .
The resulting pBLVprtbelg was infectious in transfected cells and enabled the expression of gag and gag - precursor proteins .
One sheep was injected with this mutated provirus and became positive in BLV - PCR , but no seroconversion was developed .
The prt mutation of the Japanese tumour isolates was shown to be responsible for the loss of infectivity and changed viral expression .
These results and the occurrence of this mutation in only two isolates from lymphosarcoma indicate a possible relation between the prt mutation and the induction of cell transformation .
******************************* END ABSTRACT 10900827 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1311'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10029598 ***********************************************
Overexpression of the receptor for hyaluronan - mediated motility ( RHAMM ) characterizes the malignant clone in multiple myeloma : identification of three distinct RHAMM variants .
The receptor for hyaluronan ( HA ) - mediated motility ( RHAMM ) controls motility by malignant cells in myeloma and is abnormally expressed on the surface of most malignant B and plasma cells in blood or bone marrow ( BM ) of patients with multiple myeloma ( MM ) .
RHAMM cDNA was cloned and sequenced from the malignant B and plasma cells comprising the myeloma B lineage hierarchy .
Three distinct RHAMM gene products , RHAMMFL , RHAMM-48 , and RHAMM-147 , were cloned from MM B and plasma cells .
RHAMMFL was 99 % homologous to the published sequence of RHAMM .
RHAMM-48 and RHAMM-147 variants align with RHAMMFL , but are characterized by sequence deletions of 48 bp ( 16 amino acids [ aa ] ) and 147 bp ( 49 aa ) , respectively .
The relative frequency of these RHAMM transcripts in MM plasma cells was determined by cloning of reverse - transcriptase polymerase chain reaction ( RT - PCR ) products amplified from MM plasma cells .
Of 115 randomly picked clones , 49 % were RHAMMFL , 47 % were RHAMM - 48 , and 4 % were RHAMM-147 .
All of the detected RHAMM variants contain exon 4 , which is alternatively spliced in murine RHAMM , and had only a single copy of the exon 8 repeat sequence detected in murine RHAMM .
RT - PCR analysis of sorted blood or BM cells from 22 MM patients showed that overexpression of RHAMM variants is characteristic of MM B cells and BM plasma cells in all patients tested .
RHAMM also appeared to be overexpressed in B lymphoma and B-chronic lymphocytic leukemia ( CLL ) cells .

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

<xsl:when test="@id='word_1346'">

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

RHAMM-48 was detectable in B cells from one of eight normal donors , but was undetectable in B cells of three donors with Crohn 's disease .
RHAMM-147 was undetectable in normal and Crohn 's disease B cells .
In situ RT - PCR was used to determine the number of individual cells with aggregate RHAMM transcripts .
For six patients , 29 % of BM plasma cells and 12 % of MM B cells had detectable RHAMM transcripts , while for five normal donors , only 1. 2 % of B cells expressed RHAMM transcripts .
This work suggests that RHAMMFL , RHAMM-48 , and RHAMM-147 splice variants are overexpressed in MM and other B lymphocyte malignancies relative to resting or in vivo - activated B cells , raising the possibility that RHAMM and its variants may contribute to the malignant process in B-cell malignancies such as lymphoma , CLL , and MM .
******************************* END ABSTRACT 10029598 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1347'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10966390 ***********************************************
A serine protease - encoding gene ( aprII ) of Alteromonas sp. Strain O-7 is regulated by the iron uptake regulator ( Fur ) protein .

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

<xsl:when test="@id='word_1380'">

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

To clarify whether the production of AprII ( the gene product of aprII ) is regulated by the environmental iron concentrations , this strain was cultured under iron - depleted or iron - rich conditions and the level of AprII in the culture supernatant was analyzed by Western blotting .
The production of AprII was significantly repressed under iron - rich conditions .
Northern hybridization analysis demonstrated that AprII biosynthesis was regulated by iron through the control of transcription .
These results indicate that aprII is a new member of the iron regulon and plays an important role in the iron acquisition system of the strain .
Furthermore , the gene encoding Fur was cloned and sequenced .
The deduced amino acid sequence of the cloned Fur showed high sequence similarity with that from gram - negative bacteria .
******************************* END ABSTRACT 10966390 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1381'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 14660440 ***********************************************

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

<xsl:when test="@id='word_1401'">
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

<xsl:when test="@id='word_1417'">

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

We show that this signaling inhibits the accumulation of the RNA binding protein GLD-1 .
In a genetic screen to identify other genes involved in regulating GLD-1 activity , we identified mutations in the nos-3 gene , the protein product of which is similar to the Drosophila translational regulator Nanos .
Our data demonstrate that nos-3 promotes GLD-1 accumulation redundantly with gld-2 , and that nos-3 functions genetically downstream or parallel to fbf , an inhibitor of GLD-1 translation .
We show that the GLD-1 accumulation pattern is important in controlling the proliferation versus meiotic development decision , with low GLD-1 levels allowing proliferation and increased levels promoting meiotic entry .
******************************* END ABSTRACT 14660440 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1418'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11384228 ***********************************************
The use of recombinant baculoviruses for sustained expression of human cytomegalovirus immediate early proteins in fibroblasts .
The isolation of viruses with mutations in essential genes requires that they be propagated in cells expressing the wild - type proteins .

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

<xsl:when test="@id='word_1444'">

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

In the past , we tried a number of approaches to derive human fibroblasts expressing wild - type IE2 86 , but were unable to maintain expression of a fully functional protein .
To overcome this obstacle , we developed a strategy whereby recombinant baculoviruses were used as vectors for the expression of HCMV IE proteins in primary human fibroblasts ( FFs ) .
The IE2 86 and IE1 72 cDNAs , as well as the genomic fragment of the UL122 - 123 region under the control of a chicken actin promoter , were introduced into the baculovirus genome by site - specific transposition in Escherichia coli .
Recombinant &quot; bacmid &quot; DNAs were then transfected into Sf9 cells to generate recombinant baculoviruses .
FFs infected at high m.o.i. with these baculoviruses expressed high levels of the HCMV protein for at least 1 week , as determined by immunofluorescence assays and Western blots .
Moreover , the IE2 86 protein was found to be fully functional with respect to its ability to activate the HCMV UL112 - 113 early promoter .
Recombinant baculoviruses expressing IE1 72 were also able to efficiently complement HCMV ie1 mutants .
These data demonstrate the potential of using recombinant baculoviruses as vectors for the expression of toxic viral genes in human cells and for subsequent isolation of mutant HCMV lacking these essential genes .
******************************* END ABSTRACT 11384228 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1445'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 15243700 ***********************************************
Mutation at position - 132 in the islet amyloid polypeptide ( IAPP ) gene promoter enhances basal transcriptional activity through a new CRE - like binding site .
AIMS / HYPOTHESIS :
Mutations in the islet amyloid polypeptide ( IAPP ) gene may play a potential role in the abnormal regulation or expression of the peptide .

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

<xsl:when test="@id='word_1482'">

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

METHODS :
We investigated the transcriptional activity using MIN6 cells and luciferase reporter plasmids in several culture conditions .
Key regulatory elements of the IAPP promoter region were also analysed by electrophoretic mobility shift assays ( EMSA ) .
RESULTS :
The mutant construct doubled IAPP transcriptional activity ( p &lt; 0.001 ) .
Both constructs showed severely reduced promoter activity ( four - fold decrease ) in the presence of verapamil and diazoxide .
In contrast , IAPP promoter activity was doubled after incubation with forskolin or dexamethasone , regardless of the glucose concentrations in the culture media .
EMSA revealed that the - 132 G / A mutation increased the binding affinity through two DNA - protein complexes .
In addition , a cAMP - responsive element binding protein ( CREB ) was identified by super - shift EMSA .
CONCLUSIONS / INTERPRETATION :
Our studies show that the wild - type and the mutant constructs are regulated in a similar pattern under all conditions , strongly indicating that the - 132 G / A mutation increases basal but not inducible transcription .
These results may be explained by new binding to the mutant region through CREB and other transcription factors not yet identified .
******************************* END ABSTRACT 15243700 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1483'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10902712 ***********************************************
A matrix attachment region is located upstream from the high - molecular - weight glutenin gene Bx7 in wheat ( Triticum aestivum L. ) .

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

<xsl:when test="@id='word_1523'">

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

The 5' region of this sequence contains motifs typically found in matrix attachment regions ( MARs ) in other plants .
We have shown that part of the 2.2 - kb DNA binds to wheat nuclear matrix ( NM ) in vitro , at least as strongly as a known MAR ( Adh1 ) from maize suggesting that there is a MAR upstream of Bx7 .
This MAR is approximately 800 bases in length running from - 750 to - 1560 bases , relative to the start codon .
Although the MAR is associated with a tissue - specific gene and is beside a strong tissue - specific promoter , the MAR sequence did not lead to tissue - specific expression of the beta-glucuronidase marker gene under the control of the rice actin promoter in various tissues .
Presence of the MAR was only slightly beneficial with respect to expression levels , which were not greatly altered in transient expression assays in various wheat tissues although a slight increase in the number of foci was observed in leaves , which have low transformation efficiencies .
******************************* END ABSTRACT 10902712 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1524'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 1327749 ***********************************************
Role of the open reading frames of Rous sarcoma virus leader RNA in translation and genome packaging .

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

<xsl:when test="@id='word_1551'">

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

We studied , in vivo , the role of these uORFs by changing two or three nucleotides of the three AUGs or by deleting the first uORF .
Our results show that (i) unlike most previously characterized uORFs , which decrease translation , the first uORF ( AUG1 ) of RSV acts as an enhancer of translation , since absence of the first AUG decreased translation ; AUG3 also modulates translation , probably by interfering with scanning ribosomes as described for other upstream ORFs , and mutation of AUG2 had no effect on translation .
(ii )
Mutation of each of the upstream AUGs lowered the infectivity of progeny virions .
(iii) Unexpectedly , mutation of AUG1 and/or AUG3 dramatically reduced RNA packaging by 50 - to 100 - fold , unlike mutation of AUG2 which did not alter RNA packaging efficiency .
Additional mutants in the vicinity of uORF1 and uORF3 were constructed in order to elucidate the mechanism by which uORFs affect RNA packaging : a translation model requiring uORFs 1 and 3 , and involving ribosome pausing at AUG 3 is discussed .
******************************* END ABSTRACT 1327749 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1552'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10625702 ***********************************************
Vaccinia virus gene A18R DNA helicase is a transcript release factor .

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

<xsl:when test="@id='word_1599'">

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

The purpose of the work described here was to determine a biochemical activity for the A18R protein .
Pulse - labeled transcription complexes established from intermediate virus promoters on bead - bound DNA templates were assayed for transcript release during an elongation step that contained nucleotides and various proteins .
Pulse - labeled transcription complexes elongated in the presence of only nucleotides were unable to release nascent RNA .
The addition of Wt extract during the elongation phase resulted in release of the nascent transcript , indicating that additional factors present in the Wt extract are capable of inducing transcript release .
Extract from Cts23 or mock - infected cells was unable to induce release .
The lack of release upon addition of Cts23 extract suggests that A18R is involved in release of nascent RNA .
By itself , purified polyhistidine - tagged A18R protein ( His - A18R ) was unable to induce release ; however , release did occur in the presence of purified His - A18R protein plus extract from either Cts23 or mock - infected cells .
These data taken together indicate that A18R is necessary but not sufficient for release of nascent transcripts .
We have also demonstrated that the combination of A18R protein and mock extract induces transcript release in an ATP - dependent manner , consistent with the fact that the A18R protein is an ATP - dependent helicase .
Further analysis revealed that the release activity is not restricted to a vaccinia intermediate promoter but is observed using pulse - labeled transcription complexes initiated from all three viral gene class promoters .
Therefore , we conclude that A18R and an as yet unidentified cellular factor(s) are required for the in vitro release of nascent RNA from a vaccinia virus transcription elongation complex .
******************************* END ABSTRACT 10625702 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1600'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10966398 ***********************************************
Metabolic engineering of Lactobacillus helveticus CNRZ32 for production of pure L-(+)-lactic acid .

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

<xsl:when test="@id='word_1634'">
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

<xsl:when test="@id='word_1662'">

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

One of the strains was constructed by deleting the promoter region of the ldhD gene , and the other was constructed by replacing the structural gene of ldhD with an additional copy of the structural gene ( ldhL ) of L-LDH of the same species .
The resulting strains were designated GRL86 and GRL89 , respectively .
In strain GRL89 , the second copy of the ldhL structural gene was expressed under the ldhD promoter .
The two D-LDH - negative strains produced only L-(+)-lactic acid in an amount equal to the total lactate produced by the wild type .
The maximum L-LDH activity was found to be 53 and 93 % higher in GRL86 and GRL89 , respectively , than in the wild - type strain .
Furthermore , process variables for L-(+)-lactic acid production by GRL89 were optimized using statistical experimental design and response surface methodology .
The temperature and pH optima were 41 degrees C and pH 5.9 .
At low pH , when the growth and lactic acid production are uncoupled , strain GRL89 produced approximately 20 % more lactic acid than GRL86 .
******************************* END ABSTRACT 10966398 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1663'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 12876194 ***********************************************

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

<xsl:when test="@id='word_1683'">

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

Coliphage N4 virion RNA polymerase ( vRNAP ) , the most distantly related member of the T7 - like family of RNA polymerases , is responsible for transcription of the early genes of the linear double - stranded DNA phage genome .
Escherichia coli single - stranded DNA - binding protein ( EcoSSB ) is required for N4 early transcription in vivo , as well as for in vitro transcription on super - coiled DNA templates containing vRNAP promoters .
In contrast to other DNA - dependent RNA polymerases , vRNAP initiates transcription on single - stranded , promoter - containing templates with in vivo specificity ; however , the RNA product is not displaced , thus limiting template usage to one round .
We show that EcoSSB activates vRNAP transcription at limiting single - stranded template concentrations through template recycling .
EcoSSB binds to the template and to the nascent transcript and prevents the formation of a transcriptionally inert RNA : DNA hybrid .
Using C - terminally truncated EcoSSB mutant proteins , human mitochondrial SSB ( Hsmt SSB ) , phage P1 SSB , and F episome - encoded SSB , as well as a Hsmt - EcoSSB chimera , we have mapped a determinant of template recycling to the C - terminal amino acids of EcoSSB .
T7 RNAP contains an amino - terminal domain responsible for binding the RNA product as it exits from the enzyme .
No sequence similarity to this domain exists in vRNAP .
Hereby , we propose a unique role for EcoSSB :
It functionally substitutes in N4 vRNAP for the N - terminal domain of T7 RNAP responsible for RNA binding .
******************************* END ABSTRACT 12876194 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1684'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10073708 ***********************************************
Relative levels of EBNA1 gene transcripts from the C / W , F and Q promoters in Epstein - Barr virus - transformed lymphoid cells in latent and lytic stages of infection .

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

<xsl:when test="@id='word_1723'">

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

The promoters are used differentially during the different phases of infection and establishment of the stages of latency .
This has raised questions about the regulation of the promoters and the molecular mechanisms underlying the switches between them .
To obtain a measure of the activity of the different EBNA1 transcription units in EBV - transformed cell lines of different phenotypes , RNA probes were constructed that allowed the detection and relative quantification , by RNase protection analysis , of EBNA1 transcripts initiated at Fp and Qp and , in an indirect manner , Cp / Wp .
RNase protection and PCR assays were performed with cytoplasmic RNA from B-lymphoid cell lines in latency stages I , II-III and III and after induction of the virus lytic cycle .
The experiments demonstrated that , in addition to previously identified EBNA1 transcripts , cell lines of all latency types also contained different mRNAs that carried sequences from the EBNA1 - encoding K exon .
Induction of the virus lytic cycle resulted in low levels of an FpQ / U / K-spliced transcript .
However , there was a large increase of FpQ - and FpQ / U - spliced transcripts with unknown 3' sequences .
Furthermore , a new transcript , initiated at an unidentified site 5' of the BamHI f / K cleavage site and continuing through BamHI K into the EBNA1 - encoding K exon without interruption , was produced in substantial amounts in the lytic cycle .
******************************* END ABSTRACT 10073708 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1724'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10028058 ***********************************************
Activation of HIV-1 enhancer sequence by vaccinia virus .

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

<xsl:when test="@id='word_1784'">
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

<xsl:when test="@id='word_1823'">

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

The sequence responsible for this augmentation of CAT activity was different from that recognized by HIV-1 Tat .
These data clearly demonstrated that VV transactivates HIV-1 LTR through a mechanism distinct from that of activation by HIV-1 Tat .
******************************* END ABSTRACT 10028058 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1824'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 15302954 ***********************************************

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

<xsl:when test="@id='word_1848'">
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

<xsl:when test="@id='word_1877'">

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

The effects of Z-100 on human immunodeficiency virus type 1 ( HIV-1 ) replication in human monocyte - derived macrophages ( MDMs ) are investigated in this paper .
In MDMs , Z-100 markedly suppressed the replication of not only macrophage - tropic ( M-tropic ) HIV-1 strain ( HIV-1JR-CSF ) , but also HIV-1 pseudotypes that possessed amphotropic Moloney murine leukemia virus or vesicular stomatitis virus G envelopes .
Z-100 was found to inhibit HIV-1 expression , even when added 24 h after infection .
In addition , it substantially inhibited the expression of the pNL43lucDeltaenv vector ( in which the env gene is defective and the nef gene is replaced with the firefly luciferase gene ) when this vector was transfected directly into MDMs .
These findings suggest that Z-100 inhibits virus replication , mainly at HIV-1 transcription .
However , Z-100 also downregulated expression of the cell surface receptors CD4 and CCR5 in MDMs , suggesting some inhibitory effect on HIV-1 entry .
Further experiments revealed that Z-100 induced IFN-beta production in these cells , resulting in induction of the 16 - kDa CCAAT / enhancer binding protein ( C / EBP ) beta transcription factor that represses HIV-1 long terminal repeat transcription .
These effects were alleviated by SB 203580 , a specific inhibitor of p38 mitogen - activated protein kinases ( MAPK ) , indicating that the p38 MAPK signalling pathway was involved in Z-100 - induced repression of HIV-1 replication in MDMs .
These findings suggest that Z-100 might be a useful immunomodulator for control of HIV-1 infection .
******************************* END ABSTRACT 15302954 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1878'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11123689 ***********************************************
Identification and characterization of BipA , a Bordetella Bvg - intermediate phase protein .
The Bordetella BvgAS sensory transduction system has traditionally been viewed as controlling a transition between two distinct phenotypic phases : the Bvg ( + ) or virulent phase and the Bvg(-) or avirulent phase .
Recently , we identified a phenotypic phase of Bordetella bronchiseptica that displays reduced virulence in a rat model of respiratory infection concomitant with increased ability to survive nutrient deprivation .
Characterization of this phase , designated Bvg - intermediate ( Bvg(i) ) , indicated the presence of antigens that are maximally , if not exclusively , expressed in this phase and therefore suggested the existence of a previously unidentified class of Bvg - regulated genes .
We now report the identification and characterization of a Bvg(i) phase protein , BipA ( Bvg - intermediate phase protein A ) , and its structural gene , bipA .
Reverse transcriptase - polymerase chain reaction ( RT - PCR ) analysis indicates that bipA is expressed maximally under Bvgi phase conditions and thus represents the first identified Bvgi phase gene .

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

<xsl:when test="@id='word_1923'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Although not apparent at the amino acid level , BipA is also similar to Int and Inv in that the proposed membrane - spanning domain is followed by several 90-amino-acid repeats and a distinct C - terminal domain .
Localization studies using an antibody directed against the C-terminus of BipA indicated that its C-terminus is exposed on the bacterial cell surface .

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

<xsl:when test="@id='word_1947'">

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

Comparison of a Delta bipA strain with wild - type B. bronchiseptica indicated that BipA is not required for Bvg(i) phase - specific aggregative adherence to rat lung epithelial cells in vitro or for persistent colonization of the rabbit respiratory tract in vivo .
However , our data are consistent with the hypothesis that BipA , and the Bvg(i) phase in general , play an important role in the Bordetella infectious cycle , perhaps by contributing to aerosol transmission .
******************************* END ABSTRACT 11123689 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1948'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10717479 ***********************************************
Expression of the chloroplast - localized small heat shock protein by oxidative stress in rice .

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

<xsl:when test="@id='word_1976'">

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

Southern blot analysis of genomic DNA and the result of screening of a cDNA library indicated that the Oshsp26 gene is encoded by a single gene in the rice genome .
The Oshsp26 gene was expressed following heat stress : the transcript level was highest when rice leaves were treated at high temperatures for 2h at 42 degrees C , and the transcripts became detectable after 20min and reached a maximum level after 2h .
It was also found that the Oshsp26 gene was expressed following oxidative stress even in the absence of heat stress .
Treatment of rice plants with methyl viologen ( MV ) in the light and treatment with hydrogen peroxide ( H(2)O(2) ) , either in the light or in the dark , both caused a significant accumulation of the transcripts and the protein .
Since MV treatment in the light leads to the generation of H(2)O(2) inside the chloroplast , it is likely that H(2)O(2) by itself acts to induce the expression of the Oshsp26 gene .
These results suggest that the chloroplast smHSP plays an important role in protecting the chloroplast against damage caused by oxidative stress as well as by heat stress .
******************************* END ABSTRACT 10717479 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_1977'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11988516 ***********************************************

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

<xsl:when test="@id='word_1990'">
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

<xsl:when test="@id='word_2008'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

These tails are formed primarily by poly(A) polymerase I ( PAP I ) in wild - type strains or by polynucleotide phosphorylase ( PNPase ) in PAP I - deficient strains .
In Streptomyces coelicolor it has been shown that mycelial RNAs display biochemical characteristics consistent with the presence of poly(A) tails .
To confirm the occurrence of polyadenylation , rRNA and mRNA transcripts from S. coelicolor were isolated by oligo ( dT ) - dependent RT - PCR followed by cDNA cloning .
One of the clones obtained was polyadenylated at a site corresponding to the mature 3' terminus of 16S rRNA , while two 23S rRNA cDNA clones were polyadenylated at precursor processing sites .
Other clones identified polyadenylation sites internal to the coding regions of both 16S and 23S rRNAs , and redD and actII-orf4 mRNAs .
While most rRNA cDNA clones displayed adenosine homopolymer tails , the poly(A) tails of three rRNAs and all the redD and actII-orf4 clones consisted of a variety of heteropolymers .

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

<xsl:when test="@id='word_2029'">

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

******************************* END ABSTRACT 11988516 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2030'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 14574705 ***********************************************

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

<xsl:when test="@id='word_2046'">
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

<xsl:when test="@id='word_2083'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

We characterized the expression of recombinant polyhedrin protein fused to green fluorescent protein ( GFP ) .
The polyhedrin fusion protein ( approximately 58 kDa ) was successfully expressed as an insoluble inclusion body comprising approximately 30 % of the total cellular protein .
The E. coli expressing polyhedrin - GFP fusion protein showed higher cell growth ( approximately 1.8 - fold ) and higher GFP yield ( approximately 3.5 - fold ) than the strain expressing soluble single GFP .
Interestingly , the polyhedrin fusion portion showed almost the same characteristics as the native baculoviral polyhedrin ; it was rapidly solubilized under alkaline conditions , similar to the conditions found in the insect midgut .

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

<xsl:when test="@id='word_2122'">
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

<xsl:when test="@id='word_2150'">

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

******************************* END ABSTRACT 14574705 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2151'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10972660 ***********************************************
Alleles of the alpha1 immunoglobulin gene 3' enhancer control evolution of IgA nephropathy toward renal failure .
BACKGROUND :
IgA nephropathy is the most common glomerular disease .
Mechanisms leading to its occurrence and controlling the evolution of the disease remain largely unknown .
Various genetic factors have been found , mostly implicating immunologically relevant genes ( IgH , TCR , human lymphocyte antigen , and complement loci ) .
A regulatory region recently identified downstream , the alpha1 gene of the IgH locus , was a likely candidate for the control of IgA1 production in patients .
Alleles of this region , differing by size , sequence , and orientation of the alpha1 hs1,2 transcriptional enhancer , were first identified through Southern blot hybridization .
METHODS :
We established a polymerase chain reaction ( PCR ) method suitable for routine testing that amplifies minisatellites within the alpha1 hs1 , 2 enhancer , with variable numbers of tandem repeats ( VNTR ) defining the two alleles .
This assay allowed the typing of 104 patients with IgAN and 83 healthy volunteers .
Results from typing of alpha1 hs1,2 alleles were compared with long - term clinical outcome in patients .
Enhancer alleles were compared in a luciferase reporter gene assay .
RESULTS :

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

<xsl:when test="@id='word_2179'">

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

In contrast , among patients , homozygosity for the weakest enhancer allele ( AA genotype ) was significantly correlated with a milder form of the disease , whereas the allele B was associated with severe evolution .
The minisatellite region within the alpha1 hs1,2 enhancer carried potential transcription factor - binding sites , and its duplication increased the transcriptional strength of the alpha1 hs1 , 2 allele B over that of allele A .
CONCLUSION :
Altogether , these alleles may constitute a risk factor for the prognosis of IgA nephropathy .
******************************* END ABSTRACT 10972660 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2180'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10805458 ***********************************************
Identification of a signal peptide for oryzacystatin-I .

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

<xsl:when test="@id='word_2228'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

The signal peptide appears to direct a pre - protein ( SPOC - I ; Accession No. AF164378 ) to the endoplasmic reticulum , where it is processed into the mature form of OC-I .
The start codon of SPOC-I begins 114 bp upstream from that previously published for OC-I .
A putative proteolytic site. which may yield a mature OC-I approximately 12 residues larger than previously described , has been identified within SPOC-I between Ala-26 and Glu-27 .

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

<xsl:when test="@id='word_2262'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Partially purified protein extracts from Escherichia coli expressing SPOC-I reacted with polyclonal antibodies raised against OC-I and revealed a protein of the expected molecular weight ( 15 , 355 Da ) .
In - vitro translation of SPOC-I in the presence of microsomal membranes yielded a processed product approximately 2.7 kDa smaller than the pre - protein .

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

<xsl:when test="@id='word_2312'">

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

******************************* END ABSTRACT 10805458 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2313'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11132925 ***********************************************
Differential gene expression of hepatocellular carcinoma using cDNA microarray analysis .

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

<xsl:when test="@id='word_2356'">

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

A total of 211 genes were found to be highly expressed and 147 genes were downregulated in more than 1 out of 10 of the HCC pairs .
The results were significant by two - tailed Wilcoxon test ( P &lt; or = 0.05 with 95 % confidence ) on the intensity of each DNA spot of the 10 HCC pairs .
Six genes were highly expressed and 10 genes were downregulated in more than 30 % of HCC pairs .
Results are consistent with other published reports using traditional differential display , subtractive hybridization , or immunohistochemical staining methods .
We also detected that beta-actin and glyceraldehyde 3-phosphate dehydrogenase ( G3PDH ) , which have been commonly used as an internal standard control in mRNA expression studies , were highly expressed in HCC when compared with nontumorous tissue .
It is concluded that cDNA microarray analysis is an effective method in the detection of differential gene expression in HCC .
******************************* END ABSTRACT 11132925 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2357'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11988517 ***********************************************
The clavulanic acid biosynthetic cluster of Streptomyces clavuligerus : genetic organization of the region upstream of the car gene .

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

<xsl:when test="@id='word_2381'">

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

Sequence analysis of a 12.1 kb region revealed the presence of 10 ORFs whose putative functions , according to database searches , are discussed .
Three co - transcriptional units are proposed : ORF10 - 11 , ORF12 - 13 and ORF15-16-17-18 .
Potential transcriptional terminators were identified downstream of ORF11 ( fd ) and ORF15 .
Targeted disruption of ORF10 ( cyp ) gave rise to transformants unable to produce clavulanic acid , but with a considerably higher production of cephamycin C .
Transformants inactivated at ORF14 had a remarkably lower production of clavulanic acid and similar production of cephamycin C .
Significant improvements of clavulanic acid production , associated with a drop in cephamycin C biosynthesis , were obtained with transformants of S. clavuligerus harbouring multiple copies of plasmids carrying different constructions from the ORF10 - 14 region .
This information can be used to guide strain improvement programs , blending random mutagenesis and molecular cloning , to optimize the yield of clavulanic acid .
******************************* END ABSTRACT 11988517 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2382'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 2111142 ***********************************************

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

<xsl:when test="@id='word_2404'">

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

We constructed a plasmid that contains a small piece of DNA with two vaccinia promoters running in opposite directions --a promoter from a late gene encoding an 11 K polypeptide ( P11 ) and a promoter from an early gene encoding 25K ( P25 ) .
These promoters were isolated from the Tian Tan strain of vaccinia virus and were flanked by the thymidine kinase ( TK ) sequence of the same virus .
Genes encoding the hepatitis B virus surface antigen ( HBsAg ) and the Escherichia coli beta-galactosidase ( LacZ ) were inserted downstream of the 11 K and 25 K promoters respectively so that coexpression plasmids were constructed .
Recombinant vaccinia viruses were selected directly by picking blue plaques formed under overlaying agarose medium containing X-gal .
HBsAg was expressed to high level by these recombinant viruses .
These recombinant viruses showed reduced virulence on rabbit skin and induced anti - HBs after intradermal inoculation of rabbits .
******************************* END ABSTRACT 2111142 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2405'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10548583 ***********************************************
Sézary T-cell activating factor is a Chlamydia pneumoniae - associated protein .
We previously identified a protein that was stimulatory for malignant Sézary T cells , termed Sézary T-cell activating factor ( SAF ) .
However , the identity of this protein has not been fully elucidated , nor has it 's role been determined in the pathogenesis of cutaneous T-cell lymphoma ( CTCL ) .
The basis for epidermotropism and proliferation of malignant cells in the skin of patients with CTCL is unknown .
Using a monoclonal antibody inhibitory for SAF activity , we demonstrated that SAF is present in the skin of 16 of 27 samples from patients with mycosis fungoides , the predominant form of CTCL .
In this report , the SAF determinant is demonstrated to be associated with Chlamydia pneumoniae bacteria by immunohistochemistry , immunoelectron microscopy , and culture analysis .

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

<xsl:when test="@id='word_2438'">

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

We confirmed the presence of C. pneumoniae DNA and RNA in the skin by PCR and reverse transcription - PCR and by sequence analysis of the PCR products .
The expression of the C. pneumoniae antigens and SAF appears to be associated with active disease in that C. pneumoniae antigens were absent or greatly diminished in the skin of three patients examined after Psoralen and long - wave UVA radiation treatment .
Our results suggest that SAF is a Chlamydia - associated protein and that further investigation is warranted to determine whether SAF and C. pneumoniae play a role in the pathogenesis of CTCL .
******************************* END ABSTRACT 10548583 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2439'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10548726 ***********************************************

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

<xsl:when test="@id='word_2466'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

A 1.5kb DNA fragment from Xanthomonas campestris pv. phaseoli containing fur was characterized .
fur is a single copy gene that is transcribed as a monocistronic mRNA .
The predicted amino acid sequence of Xp Fur showed extensive identity to other Fur proteins .
However , Xp Fur has many distinct features , particularly a lack of cysteine residues in the conserved metal - binding motifs and unusual modifications in the carboxy - terminus region .

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

<xsl:when test="@id='word_2479'">

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

Deduced amino acid sequences all showed the distinct features of Xp Fur .
Functionally , Xp Fur partially repressed a Fur - regulated promoter in E. coli .
Expression analysis of fur showed increased fur mRNA levels in response to a low iron growth condition .
The fur transcription start site was identified by primer extension .
******************************* END ABSTRACT 10548726 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2480'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 1358174 ***********************************************

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

<xsl:when test="@id='word_2501'">
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

<xsl:when test="@id='word_2524'">
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

<xsl:when test="@id='word_2557'">

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

While one of the Xenopus fork head sequences , XFD-3 , represents the Xenopus counterpart to rat HNF-3 beta , all other sequences encode novel types of fork head related proteins .
Here we report on XFD-1 , a DNA binding protein which can bind to the HNF-3 alpha target sequence .
Analysis of temporal and spatial expression revealed that the gene is activated at blastula stage and that transcripts are localized in a rather thin stripe of cells invaginating the dorsal blastopore lip ( organizer ) during gastrulation .
XFD-1 mRNA is localized within the notochord and , by the end of neurulation , is no longer detectable .
In the animal cap assay the gene is activated by incubation with the vegetalizing factor ( activin A ) but not with bFGF .
******************************* END ABSTRACT 1358174 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2558'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 12021174 ***********************************************
Gene expression profiling of testosterone and estradiol-17 beta - induced prostatic dysplasia in Noble rats and response to the antiestrogen ICI 182,780 .
We previously demonstrated that 1) treatment of Noble rats for 16 wk with testosterone ( T ) and estradiol-17 beta ( E2 ) led to 100 % incidence of dorsolateral prostate ( DLP ) dysplasia and hyperprolactinemia and 2) blockade of PRL release with bromocriptine cotreatment significantly lowered the incidence of DLP dysplasia .
In the current study , we sought to determine whether E2 exerts direct effects , independent of PRL , in this model system .

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

<xsl:when test="@id='word_2596'">

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

ICI cotreatment completely prevented DLP dysplasia development but it also blocked hyperprolactinemia in the dual hormone - treated rats .
Gene profiling with an 1185 gene rat cDNA array identified approximately 100 genes displaying &gt; or = 3 - fold changes in rat lateral prostates ( LPs ) following T + E2 treatment .
Significantly more genes were up - regulated ( 77 ) than down - regulated ( 14 ) , reflecting cellular / molecular changes associated with enhanced cell proliferation , DNA damage , heightened protein and RNA synthesis , increased energy metabolism , and activation of several proto - oncogenes and intracellular signaling pathways .
Post hoc analyses , using quantitative real - time RT - PCR , corroborated differential expression of eight genes , exhibiting three different patterns of altered expression .
Genes encoding the early growth response protein 1 and metalloendopeptidase meprin beta - subunit were similarly altered in T + E2 - and T + E2 + ICI - treated animals when compared with untreated controls .
In contrast , transcripts of fos - related antigen-2 , growth arrest and DNA damage - inducible protein - 45 , and signal transducer and activator of transcription-3 were significantly increased in the LPs of T + E2 - treated animals , but the increases were reversed by cotreatment with ICI .
Differential expression of fos - related antigen-2 and growth arrest and DNA damage - inducible protein-45 were further confirmed at the protein level by immunohistochemistry .
Lastly , levels of A-RAF , VIP-1 receptor , and calpastatin mRNA were distinctly lessen in rat LPs under T + E2 influence , but rebound with ICI cotreatment .
In conclusion , our findings further implicated pituitary PRL in the induction of dysplasia in rat LP .
Gene profiling provided clues that molecular events related to enhancement of cell proliferation , DNA damage , and activation of proto - oncogenes and transforming factors may be causally linked to the genesis of LP dysplasia in this rat model .
******************************* END ABSTRACT 12021174 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2597'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10483053 ***********************************************
Identification of four variants in the tryptophan hydroxylase promoter and association to behavior .
One of the most replicated findings in biological psychiatry is the observation of lower 5-hydroxyindoleacetic acid concentrations , the major metabolite of serotonin , in the brain and cerebrospinal fluid of subjects with impulsive aggression .
Tryptophan hydroxylase ( TPH ) is the rate - limiting enzyme in the synthesis of serotonin , however functional variants have not been reported from the coding sequence of this gene .
Therefore , we screened the human TPH promoter ( TPH-P ) for genetic variants which could modulate TPH gene transcription .

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

<xsl:when test="@id='word_2626'">

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

Four common polymorphisms were identified : - 7180T &gt; G , -7065C&gt; T,-6526A&gt; G , and -5806G&gt; T ( designated as nucleotides upstream of the translation start site ) .
In the Finns , the four polymorphisms had a minor allele frequency of 0.40 and in this population linkage disequilibrium between the four loci was complete .
In the other populations the minor allele frequencies ranged from 0.40 to 0.45 .
TPH -6526A&gt; G genotype was determined in 167 unrelated Finnish offenders and 153 controls previously studied for the TPH IVS7 + 779C &gt; A polymorphism .
A significant association was observed between - 6526A &gt; G and suicidality in the offenders .
TPH -6526A&gt; G and the previously reported intron seven polymorphism , TPH IVS7 + 779C &gt; A , exhibited a normalised linkage disequilibrium of 0.89 in Finns .
Normalized linkage disequilibrium was reduced in other populations , being 0.49 and 0.21 in Italians and American Indians , respectively .
In conclusion , four TPH-P variants were identified which can be used for haplotype - based analysis to localize functional TPH alleles influencing behavior .
******************************* END ABSTRACT 10483053 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2627'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 15367632 ***********************************************
Characterization of a baculovirus lacking the alkaline nuclease gene .

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

<xsl:when test="@id='word_2666'">

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

These activities are thought to be involved in DNA recombination and replication .
To investigate the role of AN in AcMNPV replication , the lambda Red system was used to replace the an open reading frame with a chloramphenicol acetyltransferase gene ( cat ) and a bacmid containing the AcMNPV genome in Escherichia coli .
The AcMNPV an knockout bacmid ( vAcAN-KO / GUS ) was unable to propagate in Sf9 cells , although an an - rescued bacmid ( vAcAN-KO / GUS-Res ) propagated normally .
In addition , the mutant did not appear to produce budded virions .
These data indicated that an is an essential baculovirus gene .
Slot blot and DpnI assays of DNA replication in Sf9 cells transfected with vAcAN - KO / GUS , vAcAN - KO / GUS - Res , and a wild - type bacmid showed that the vAcAN - KO / GUS bacmid was able to replicate to levels similar to those seen with the vAcAN-KO / GUS-Res and wild - type bacmids at early stages posttransfection .
However , at later time points DNA did not accumulate to the levels seen with the repaired or wild - type bacmids .
Northern analysis of Sf9 cells transfected with bacmid vAcAN-KO / GUS showed that transcription of late and very late genes was lower at later times posttransfection relative to the results seen with wild - type and vAcAN-KO / GUS - Res bacmids .
These data suggest that the an gene might be involved in the maturation of viral DNA or packaging of the DNA into virions .
******************************* END ABSTRACT 15367632 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2667'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10542165 ***********************************************
Activation of the cryptic aac(6')-Iy aminoglycoside resistance gene of Salmonella by a chromosomal deletion generating a transcriptional fusion .

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

<xsl:when test="@id='word_2682'">

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

BM4361 was susceptible to aminoglycosides , whereas BM4362 was resistant to tobramycin owing to synthesis of a 6'-N-acetyltransferase type I [ AAC ( 6')-I ] .
Comparative analysis of nucleotide sequences , pulsed - field gel electrophoresis patterns , and Southern hybridizations indicated that the chromosomal aac(6' ) - Iy genes for the enzyme in both strains were identical and that BM4362 derived from BM4361 following a ca. 60-kb deletion that occurred 1.5 kb upstream from the resistance gene .
Northern hybridizations showed that aac(6')-Iy was silent in BM4361 and highly expressed in BM4362 due to a transcriptional fusion .
Primer extension mapping identified the transcriptional start site for aac(6' ) - Iy in BM4362 : 5 bp downstream from the promoter of the nmpC gene .
Study of the distribution of aac(6' ) - Iy by PCR and Southern hybridization with a specific probe indicated that the gene , although not found in S. enterica subsp. arizonae , was specific for Salmonella .
In this bacterial genus , aac(6' ) - Iy was located downstream from a cluster of seven open reading frames analogous to an Escherichia coli locus that encodes enzymes putatively involved in carbohydrate transport or metabolism .
This genomic environment suggests a role in the catabolism of a specific sugar for AAC(6')-Iy in Salmonella .
******************************* END ABSTRACT 10542165 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2683'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 12486060 ***********************************************
Multiple promoter inversions generate surface antigenic variation in Mycoplasma penetrans .
Mycoplasma penetrans is a newly identified species of the genus MYCOPLASMA :

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

<xsl:when test="@id='word_2702'">

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

M. penetrans changes its surface antigen profile with high frequency .
The changes originate from ON &lt; = = &gt; OFF phase variations of the P35 family of surface membrane lipoproteins .
The P35 family lipoproteins are major antigens recognized by the human immune system during M. penetrans infection and are encoded by the mpl genes .
Phase variations of P35 family lipoproteins occur at the transcriptional level of mpl genes ; however , the precise genetic mechanisms are unknown .
In this study , the molecular mechanisms of surface antigen profile change in M. penetrans were investigated .
The focus was on the 46 - kDa protein that is present in M. penetrans strain HF-2 but not in the type strain , GTU .
The 46 - kDa protein was the product of a previously reported mpl gene , pepIMP13 , with an amino - terminal sequence identical to that of the P35 family lipoproteins .
Nucleotide sequencing analysis of the pepIMP13 gene region revealed that the promoter - containing 135 - bp DNA of this gene had the structure of an invertible element that functioned as a switch for gene expression .
In addition , all of the mpl genes of M. penetrans HF-2 were identified using the whole - genome sequence data that has recently become available for this bacterium .
There are at least 38 mpl genes in the M. penetrans HF-2 genome .
Interestingly , most of these mpl genes possess invertible promoter - like sequences , similar to those of the pepIMP13 gene promoter .
A model for the generation of surface antigenic variation by multiple promoter inversions is proposed .
******************************* END ABSTRACT 12486060 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2703'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10200308 ***********************************************

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

<xsl:when test="@id='word_2718'">

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

Alphavirus vectors are being developed for possible human vaccine and gene therapy applications .
We have sought to advance this field by devising DNA - based vectors and approaches for the production of recombinant vector particles .
In this work , we generated a panel of alphavirus vector packaging cell lines ( PCLs ) .
These cell lines were stably transformed with expression cassettes that constitutively produced RNA transcripts encoding the Sindbis virus structural proteins under the regulation of their native subgenomic RNA promoter .
As such , translation of the structural proteins was highly inducible and was detected only after synthesis of an authentic subgenomic mRNA by the vector - encoded replicase proteins .
Efficient production of biologically active vector particles occurred after introduction of Sindbis virus vectors into the PCLs .
In one configuration , the capsid and envelope glycoproteins were separated into distinct cassettes , resulting in vector packaging levels of 10(7) infectious units / ml , but reducing the generation of contaminating replication - competent virus below the limit of detection .
Vector particle seed stocks could be amplified after low multiplicity of infection of PCLs , again without generating replication - competent virus , suggesting utility for production of large - scale vector preparations .
Furthermore , both Sindbis virus - based and Semliki Forest virus - based vectors could be packaged with similar efficiency , indicating the possibility of developing a single PCL for use with multiple alphavirus - derived vectors .
******************************* END ABSTRACT 10200308 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2719'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10051628 ***********************************************
Gene expression , synthesis , and secretion of interleukin 18 and interleukin 1beta are differentially regulated in human blood mononuclear cells and mouse spleen cells .
Interleukin ( IL ) - 18 , formerly called interferon gamma ( IFN-gamma ) - inducing factor , is biologically and structurally related to IL-1beta .
A comparison of gene expression , synthesis , and processing of IL-18 with that of IL-1beta was made in human peripheral blood mononuclear cells ( PBMCs ) and in human whole blood .
Similar to IL-1beta , the precursor for IL-18 requires processing by caspase 1 .
In PBMCs , mature but not precursor IL-18 induces IFN-gamma ; in whole human blood stimulated with endotoxin , inhibition of caspase 1 reduces IFN-gamma production by an IL-1beta - independent mechanism .

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

<xsl:when test="@id='word_2742'">

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

Western blotting of endotoxin - stimulated PBMCs revealed processed IL-1beta in the supernatants via an caspase 1 - dependent pathway .
However , in the same supernatants , only unprocessed precursor IL-18 was found .
Unexpectedly , precursor IL-18 was found in freshly obtained PBMCs and constitutive IL-18 gene expression was present in whole blood of healthy donors , whereas constitutive IL-1beta gene expression is absent .
Similar to human PBMCs , mouse spleen cells also constitutively contained the preformed precursor for IL-18 and expressed steady - state IL-18 mRNA , but there was no IL-1beta protein and no spontaneous gene expression for IL-1beta in these same preparations .
We conclude that although IL-18 and IL-1beta are likely members of the same family , constitutive gene expression , synthesis , and processing are different for the two cytokines .
******************************* END ABSTRACT 10051628 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2743'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10487212 ***********************************************
Structure and promoter / leader deletion analysis of mirabilis mosaic virus ( MMV ) full - length transcript promoter in transgenic plants .

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

<xsl:when test="@id='word_2780'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

The boundaries required for maximal promoter expression were defined by 5' and 3' deletion analysis of the MMV promoter fragments coupled to a GUS reporter gene .

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

<xsl:when test="@id='word_2805'">

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

A 360 bp FLt promoter fragment ( sequence - 297 to + 63 from the transcription start site ) was found sufficient for strong promoter activity .
The transcription start site ( TSS ) of the MMV FLt promoter was determined by primer extension analysis using total RNA isolated from transgenic plants containing a MMV promoter : uidA fusion gene .
Analysis of the 5' and 3' deletion constructs showed that an upstream region ( sequence - 248 to - 193 from the transcription start site ) is required for the MMV FLt promoter activity along with the as-1 , TATA box regions .
In addition , a 31 bp sequence ( + 33 to + 63 from the transcription start site ) located downstream of a TATA box is also essential for the maximum expression of the MMV FLt promoter .
Analysis of transcripts ( mRNA ) from these chimeric constructs also indicated that the MMV FLt promoter fragment ( - 297 to + 63 from the transcription start site ) has the highest promoter activity .
In a comparative analysis the MMV FLt promoter showed much greater activity than the CaMV 35S promoter .
******************************* END ABSTRACT 10487212 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2806'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 15576670 ***********************************************

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

<xsl:when test="@id='word_2822'">

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

German cockroach extract synergistically regulates tumor necrosis factor - alpha ( TNF-alpha ) - induced interleukin ( IL ) - 8 expression in human airway epithelial cells .
The IL-8 promoter contains nuclear factor ( NF ) - kappaB , activating protein ( AP ) - 1 , and NF for IL-6 ( NF-IL6 ) transcription factor binding regions .
Because cockroach extract activates extracellular regulated kinase ( ERK ) , a known activator of AP-1 and NF-IL6 , we focused on the regulation of these transcription factors .
Although TNF-alpha and cockroach extract both increased AP-1 translocation , mutation of the AP-1 site in the context of the wild - type promoter had no effect on cockroach extract - induced synergy .
Mutation of the NF-IL6 site in the context of the wild - type IL-8 promoter , or overexpression of a dominant - negative NF-IL6 mutant , each abolished cockroach extract - induced synergy .
Cockroach extract induced NF-IL6 translocation and DNA binding , an effect that was further increased in the presence of TNF-alpha .
Cockroach extract - induced regulation of NF-IL6 was due to active serine proteases in the extract as well as activation of protease activated receptor ( PAR ) - 2 , but not PAR-1 .
Chemical inhibition of ERK also attenuated cockroach extract - induced NF-IL6 - DNA binding .
We conclude that proteases in German cockroach extract regulate PAR-2 and ERK to increase NF-IL6 activity and synergistically regulate TNF-alpha - induced IL-8 promoter activity in human airway epithelium .
******************************* END ABSTRACT 15576670 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2823'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11123694 ***********************************************
Differential fluorescence induction reveals Streptococcus pneumoniae loci regulated by competence stimulatory peptide .

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

<xsl:when test="@id='word_2848'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Competence stimulatory peptide ( CSP ) was used as the model inducing system to identify differentially expressed genes .

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

<xsl:when test="@id='word_2884'">

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

S. pneumoniae carrying the library were induced with CSP and enriched for green fluorescent protein ( GFP ) - expressing bacteria using fluorescence - activated cell sorting .
A total of 886 fluorescent clones was screened , and 12 differentially activated promoter elements were identified .
Sequence analysis of these clones revealed that three were associated with novel competence loci , one of which we show is essential for DNA uptake , and six are known CSP - inducible promoters .
We also explored whether competence proteins have a role in virulence and found that mutations in three CSP - inducible genes resulted in attenuated virulence phenotypes in either of two murine infection models .
These results demonstrate the utility of DFI as a method for identifying differentially expressed genes in S. pneumoniae and the potential utility of applying DFI to other Gram - positive bacteria .
******************************* END ABSTRACT 11123694 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2885'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11823218 ***********************************************
Osmotically regulated synthesis of the compatible solute ectoine in Bacillus pasteurii and related Bacillus spp. By using natural - abundance ( 13)C-nuclear magnetic resonance spectroscopy and high - performance liquid chromatography ( HPLC ) analysis we have investigated the types of compatible solutes that are synthesized de novo in a variety of Bacillus species under high - osmolality growth conditions .
Five different patterns of compatible solute production were found among the 13 Bacillus species we studied .

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

<xsl:when test="@id='word_2952'">

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

Hence , the ability to produce the tetrahydropyrimidine ectoine under hyperosmotic growth conditions is widespread within the genus Bacillus and closely related taxa .
To study ectoine biosynthesis within the group of Bacillus species in greater detail , we focused on B. pasteurii .
We cloned and sequenced its ectoine biosynthetic genes ( ectABC ) .
The ectABC genes encode the diaminobutyric acid acetyltransferase ( EctA ) , the diaminobutyric acid aminotransferase ( EctB ) , and the ectoine synthase ( EctC ) .
Together these proteins constitute the ectoine biosynthetic pathway , and their heterologous expression in B. subtilis led to the production of ectoine .
Northern blot analysis demonstrated that the ectABC genes are genetically organized as an operon whose expression is strongly enhanced when the osmolality of the growth medium is raised .
Primer extension analysis allowed us to pinpoint the osmoregulated promoter of the B. pasteurii ectABC gene cluster .
HPLC analysis of osmotically challenged B. pasteurii cells revealed that ectoine production within this bacterium is finely tuned and closely correlated with the osmolality of the growth medium .
These observations together with the osmotic control of ectABC transcription suggest that the de novo synthesis of ectoine is an important facet in the cellular adaptation of B. pasteurii to high - osmolarity surroundings .
******************************* END ABSTRACT 11823218 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2953'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11844764 ***********************************************

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

<xsl:when test="@id='word_2965'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

The bacterium Vibrio natriegens can double with a generation time of less than 10 min ( R. G. Eagon , J. Bacteriol. 83 : 736 - 737 , 1962 ) , a growth rate that requires an extremely high rate of protein synthesis .
We show here that V. natriegens ' high potential for protein synthesis results from an increase in ribosome numbers with increasing growth rate , as has been found for other bacteria .
We show that V. natriegens contains a large number of rRNA operons , and its rRNA promoters are extremely strong .
The V. natriegens rRNA core promoters are at least as active in vitro as Escherichia coli rRNA core promoters with either E. coli RNA polymerase ( RNAP ) or V. natriegens RNAP , and they are activated by UP elements , as in E. coli .

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

<xsl:when test="@id='word_2982'">

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

We conclude that the high capacity for ribosome synthesis in V. natriegens results from a high capacity for rRNA transcription , and the high capacity for rRNA transcription results , at least in part , from the same factors that contribute most to high rates of rRNA transcription in E. coli , i.e. , high gene dose and strong activation by UP elements and Fis .
******************************* END ABSTRACT 11844764 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_2983'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10233959 ***********************************************
Papillomavirus capsid protein expression level depends on the match between codon usage and tRNA availability .

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

<xsl:when test="@id='word_3020'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

The codon composition of PV late genes is quite different from that of most mammalian genes .
To test the possibility that PV late gene codon composition determines the efficiency of PV late gene expression in some cell types , synthetic bovine papillomavirus type 1 ( BPV1 ) late genes were constructed with codon composition modified to resemble the typical mammalian gene .
Expression of these genes from a strong promoter in Cos-1 cells was compared with expression of wild - type BPV1 late genes from the same promoter .
Both unmodified and modified PV late genes were transcribed in Cos-1 cells , but only the codon - modified genes were translated .
In vitro translation of wild - type but not synthetic BPV1 L1 mRNA was markedly enhanced by addition of aminoacyl-tRNAs .
Codon composition thus limits BPV1 late gene translation in Cos-1 cells , and this limitation can be overcome by modification of the codon composition of the genes or by provision of excess tRNA .
Replacement of codons in the green fluorescent protein ( gfp ) gene with those frequently used in PV late genes did not alter gfp transcription in Cos-1 cells but almost abolished translation , supporting the hypothesis that the observed differences in efficiency of translation of modified and unmodified PV capsid genes were related to codon usage rather than mRNA structure .

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

<xsl:when test="@id='word_3060'">

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

******************************* END ABSTRACT 10233959 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_3061'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10021339 ***********************************************

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

<xsl:when test="@id='word_3081'">

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

Early developmental patterning of the Drosophila embryo is driven by the activities of a diverse set of maternally and zygotically derived transcription factors , including repressors encoded by gap genes such as Krüppel , knirps , giant and the mesoderm - specific snail .
The mechanism of repression by gap transcription factors is not well understood at a molecular level .
Initial characterization of these transcription factors suggests that they act as short - range repressors , interfering with the activity of enhancer or promoter elements 50 to 100 bp away .
To better understand the molecular mechanism of short - range repression , we have investigated the properties of the Giant gap protein .
We tested the ability of endogenous Giant to repress when bound close to the transcriptional initiation site and found that Giant effectively represses a heterologous promoter when binding sites are located at - 55 bp with respect to the start of transcription .
Consistent with its role as a short - range repressor , as the binding sites are moved to more distal locations , repression is diminished .
Rather than exhibiting a sharp ' step - function ' drop - off in activity , however , repression is progressively restricted to areas of highest Giant concentration .
Less than a two - fold difference in Giant protein concentration is sufficient to determine a change in transcriptional status of a target gene .
This effect demonstrates that Giant protein gradients can be differentially interpreted by target promoters , depending on the exact location of the Giant binding sites within the gene .
Thus , in addition to binding site affinity and number , cis element positioning within a promoter can affect the response of a gene to a repressor gradient .
We also demonstrate that a chimeric Gal4 - Giant protein lacking the basic / zipper domain can specifically repress reporter genes , suggesting that the Giant effector domain is an autonomous repression domain .
******************************* END ABSTRACT 10021339 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_3082'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11179301 ***********************************************
Genetic loci of Streptococcus mitis that mediate binding to human platelets .
The direct binding of bacteria to platelets is a postulated major interaction in the pathogenesis of infective endocarditis .

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

<xsl:when test="@id='word_3115'">

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

Two distinct loci were found to affect platelet binding .
The first contains a gene ( pblT ) encoding a highly hydrophobic , 43 - kDa protein with 12 potential membrane - spanning segments .
This protein resembles members of the major facilitator superfamily of small - molecule transporters .
The second platelet binding locus consists of an apparent polycistronic operon .
This region includes genes that are highly similar to those of Lactococcus lactis phage r1t and Streptococcus thermophilus phage 01205 .
Two genes ( pblA and pblB ) encoding large surface proteins are also present .
The former encodes a 107 - kDa protein containing tryptophan - rich repeats , which may serve to anchor the protein within the cell wall .
The latter encodes a 121 - kDa protein most similar to a tail fiber protein from phage 01205 .
Functional mapping by insertion - duplication mutagenesis and gene complementation indicates that PblB may be a platelet adhesin and that expression of PblB may be linked to that of PblA .
The combined data indicate that at least two genomic regions contribute to platelet binding by S. mitis .
One encodes a probable transmembrane transporter , while the second encodes two large surface proteins resembling structural components of lysogenic phages .
******************************* END ABSTRACT 11179301 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_3116'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 15044262 ***********************************************
Epigenetic and genomic imprinting analysis in nuclear transfer derived Bos gaurus / Bos taurus hybrid fetuses .
Somatic cell nuclear transfer ( NT ) in cattle is an inefficient process , whereby the production of calves is hindered by low pregnancy rates as well as fetal and placental abnormalities .
Interspecies models have been previously used to facilitate the identification of single nucleotide polymorphisms ( SNPs ) within coding regions of genes to discriminate between parental alleles in the offspring .

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

<xsl:when test="@id='word_3156'">

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

Analysis of NT and control pregnancies indicated disruption of genomic imprinting at the X inactivation - specific transcript ( XIST ) locus in the chorion , but not the fetus of clones , whereas proper allelic expression of the insulin - like growth factor II ( IGF2 ) and gene trap locus 2 ( GTL2 ) loci was maintained in both the fetus and placenta .
Analysis of the XIST differentially methylated region ( DMR ) in clones indicated normal patterns of methylation ; however , bisulfite sequencing of the satellite I repeat element and epidermal cytokeratin promoter indicated hypermethylation in the chorion of clones when compared with controls .
No differences were detected in methylation levels in the fetus proper .
These results indicate that the nuclear transfer process affects gene expression patterns in the trophectoderm - and inner cell mass - derived tissues to different extents .
******************************* END ABSTRACT 15044262 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_3157'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11849541 ***********************************************
Regulation of gene expression in Vibrio cholerae by ToxT involves both antirepression and RNA polymerase stimulation .

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

<xsl:when test="@id='word_3203'">

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

This work examined interactions between ToxT and the promoters of ctx and tcpA genes .
We found that a minimum of three direct repeats of the sequence TTTTGAT is required for ToxT - dependent activation of the ctx promoter , and that the region from - 85 to - 41 of the tcpA promoter contains elements that are responsive to ToxT - dependent activation .
The role of H-NS in transcription of ctx and tcpA was also analysed .
The level of activation of ctx-lacZ in an E. coli hns - strain was greatly increased even in the absence of ToxT , and was further enhanced in the presence of ToxT .
In contrast , H-NS plays a lesser role in the regulation of the tcpA promoter .
Electrophoretic mobility shift assays demonstrated that 6x His - tagged ToxT directly , and specifically , interacts with both the ctx and tcpA promoters .
DNase I footprinting analysis suggests that there may be two ToxT binding sites with different affinity in the ctx promoter and that ToxT binds to - 84 to - 41 of the tcpA promoter .
In vitro transcription experiments demonstrated that ToxT alone is able to activate transcription from both promoters .
We hypothesize that under conditions appropriate for ToxT - dependent gene expression , ToxT binds to AT - rich promoters that may have a specific secondary conformation , displaces H-NS and stimulates RNA polymerase resulting in transcription activation .
******************************* END ABSTRACT 11849541 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_3204'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11161030 ***********************************************
LEPS2 , a phosphorus starvation - induced novel acid phosphatase from tomato .
Phosphate ( Pi ) is one of the least available plant nutrients found in the soil .
A significant amount of phosphate is bound in organic forms in the rhizosphere .
Phosphatases produced by plants and microbes are presumed to convert organic phosphorus into available Pi , which is absorbed by plants .

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

<xsl:when test="@id='word_3233'">

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

LePS2 is a member of a small gene family in tomato .
The cDNA is 942 bp long and contains an open reading frame encoding a 269-amino acid polypeptide .
The amino acid sequence of LePS2 has a significant similarity with a phosphatase from chicken .
Distinct regions of the peptide also share significant identity with the members of HAD and DDDD super families of phosphohydrolases .
Many plant homologs of LePS2 are found in the databases .
The LePS2 transcripts are induced rapidly in tomato plant and cell culture in the absence of Pi .
However , the induction is repressible in the presence of Pi .
Divided root studies indicate that internal Pi levels regulate the expression of LePS2 .
The enhanced expression of LePS2 is a specific response to Pi starvation , and it is not affected by starvation of other nutrients or abiotic stresses .
The bacterially ( Escherichia coli ) expressed protein exhibits phosphatase activity against the synthetic substrate p-nitrophenyl phosphate .
The pH optimum of the enzyme activity suggests that LePS2 is an acid phosphatase .
******************************* END ABSTRACT 11161030 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_3234'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11939908 ***********************************************

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

<xsl:when test="@id='word_3249'">

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

The ( murine ) type I interferon ( IFN ) receptor , muIfnar-2 , is expressed ubiquitously , and exists as both transmembrane and soluble forms .
In the present study we show that the gene encoding muIfnar-2 spans approx. 33 kb on mouse chromosome 16 , and consists of nine exons and eight introns .
The three mRNA splice variants resulting in one transmembrane ( muIfnar-2c ) and two soluble ( muIfnar-2a/2a' ) mRNA isoforms are generated by alternative RNA processing of the muIfnar-2 gene .
Treatment of a range of murine cell lines with a combination of type I and II IFN showed that the muIfnar - 2a and - 2c mRNA isoforms were up - regulated independently of each other in L929 fibroblasts and hepa - 1c1c7 hepatoma cells , but not in M1 myeloid leukaemia cells .
Analysis of the 5' flanking region of muIfnar-2 using promoter - luciferase reporter constructs defined three regulatory regions : a region proximal to exon 1 , conferring high basal expression , a distal region conferring inducible expression , and a negative regulatory region between the two .
These data represent the first promoter analysis of a type I IFN receptor and , taken together with our previous data demonstrating high expression levels and dual biological functions for muIfnar - 2a protein , suggests that the regulation of muIfnar-2 isoform expression may be an important way of modulating type I IFN responses .
******************************* END ABSTRACT 11939908 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_3250'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 12395150 ***********************************************
Selection of potential therapeutics based on in vivo spatiotemporal transcription patterns of heme oxygenase-1 .

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

<xsl:when test="@id='word_3283'">

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

Metalloporphyrins ( Mps ) have been well characterized as competitive inhibitors of HO and have been evaluated as potential chemopreventive agents for neonatal jaundice .
However , in addition to reducing HO activity , many Mps have been shown to increase HO-1 transcription , which would likely reduce their potential therapeutic utility .
The differential effects of Mps on the transcription of HO-1 were therefore evaluated in living transgenic ( Tg ) reporter mice .
Of the compounds evaluated , we observed that zinc bis - glycol porphyrin ( ZnBG ) , a potent inhibitor of HO enzyme activity , did not alter HO-1 transcription patterns in Tg mice .
Whole body images of HO-1 transcription patterns did , however ; reveal increases in HO-1 transcription in Tg mice after treatment with other Mps , heme and cadmium chloride ( CdCl ( 2 ) ) .
Intravenous injections of CdCl(2) resulted in expression patterns that differed in tempo and location from those observed in Tg mice treated with intraperitoneal injections .
Spatiotemporal analyses of transcriptional regulation in living animals accelerated the assessment of an adverse effect of Mps by revealing different patterns of HO-1 transcription .
Among the known inhibitors of HO enzyme activity that were evaluated in this study , ZnBG did not significantly affect HO-1 transcription and therefore may be well suited for the prevention of neonatal jaundice .
******************************* END ABSTRACT 12395150 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_3284'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10524754 ***********************************************

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

<xsl:when test="@id='word_3301'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>

Biosynthesis of the food additive nisin , a posttranslationally modified peptide antibiotic existing as two natural variants ( A and Z ) , requires eleven genes ( nisA / ZBTCIPRKFEG ) involved in modification , secretion , regulation and self - immunity .
The suggested self - immunity genes ( nisFEG ) of the nisin Z producer Lactococcus lactis subsp. lactis N8 were cloned and sequenced .
Putative binding sites of the NisR transcription factor were recognized upstream of the nisF promoter .
The hydrophilic NisF protein was expressed in Escherichia coli and shown to be associated with the membrane .

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

<xsl:when test="@id='word_3330'">

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

This showed that NisF alone does not protect against nisin .
Overexpression of the nisF gene in the N8 nisin producer did not affect the level of nisin immunity , indicating that the wild - type amount of NisF is not limiting the level of nisin immunity .
Production of antisense-nisEG or antisense - nisG RNA in L. lactis N8 resulted in severe reduction in the level of nisFEG mRNA and a clearly reduced immunity showing that the nisFEG transcript is important for development of nisin self - immunity .
******************************* END ABSTRACT 10524754 ***********************************************

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

<xsl:template match="cell_component:markable" mode="opening">
<xsl:value-of select="mmax:startBold()"/>
<xsl:value-of select="mmax:addLeftMarkableHandle(@mmax_level, @id, '[')"/>
<xsl:value-of select="mmax:endBold()"/>
</xsl:template>

<xsl:template match="cell_component:markable" mode="closing">
<xsl:value-of select="mmax:startBold()"/>
<xsl:value-of select="mmax:addRightMarkableHandle(@mmax_level, @id, ']')"/>
<xsl:value-of select="mmax:endBold()"/>
</xsl:template>
</xsl:stylesheet>
                
                
                   

