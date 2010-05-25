<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
xmlns:mmax="org.eml.MMAX2.discourse.MMAX2DiscourseLoader"
xmlns:bioentity="www.eml.org/NameSpaces/bioentity">
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
***************************** BEGIN ABSTRACT 15746062 ***********************************************
Enhanced sensitivity to the HER1 / epidermal growth factor receptor tyrosine kinase inhibitor erlotinib hydrochloride in chemotherapy - resistant tumor cell lines .

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

<xsl:when test="@id='word_29'">

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

In phase II clinical studies , oral erlotinib monotherapy has shown antitumor activity in patients with advanced non - small cell lung cancer , head and neck cancer , and ovarian cancer after the failure of standard chemotherapy .
We hypothesized that some tumors treated with multiple cytotoxic therapies may become more dependent on the HER1 / EGFR signaling pathways for survival .
EXPERIMENTAL DESIGN : The growth - inhibitory effect of erlotinib was tested on 10 pairs of chemosensitive , parental , and chemoresistant tumor cell lines .
RESULTS : Enhanced sensitivity to erlotinib was observed in the doxorubicin - resistant human breast cancer cell line MCF-7 , paclitaxel - resistant human ovarian carcinoma cell line A2780 , and cisplatin - resistant human cervical carcinoma cell line ME180 .
The IC(50 ) values of erlotinib in the resistant cell lines were 2 - to 20 - fold lower than those in the corresponding parental cell lines .
This enhanced sensitivity to erlotinib correlated with higher HER1 / EGFR and phospho-HER1 / EGFR expression when compared with the corresponding parental cell lines .
Acquired resistance to cytotoxic agents was not associated with cross - resistance to erlotinib .
AE-ME180 / CDDP - resistant xenografts showed greater sensitivity to erlotinib than parental ME180 xenografts did .
CONCLUSIONS : Our findings suggest that acquired resistance to cytotoxic therapy in some tumors is associated with enhanced sensitivity to HER1 / EGFR inhibitors , which correlates with increased HER1 / EGFR expression .
These data may explain some of the observed clinical activity of HER1 / EGFR inhibitors in patients previously treated with multiple therapies .
HER1 / EGFR tyrosine kinase inhibitors may be more effective as second - or third - line treatment for certain patients with tumors that were previously treated with multiple chemotherapy regimens .
******************************* END ABSTRACT 15746062 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_30'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10639311 ***********************************************
Cell cycle - dependent stimulation of the HIV-1 promoter by Tat - associated CAK activator .

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

<xsl:when test="@id='word_65'">

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

Importantly , viral gene activation has been shown to be stimulated in mitogenically induced cells , although the link between cell cycle regulation and viral gene activation is unclear .
We reported a Tat - associated CAK / CTD kinase from mitogenically induced primary human T-cells ( TTK ) ( S.
Nekhai et al. , 1997 , J. Virol . 71 , 7436-7441 ) .
Here , biological activity of the kinase has been studied by direct microinjection at the individual -cell level .
The TTK - dependent Tat response is maximal during G1 phase as shown by co - injection with Tat protein in cells synchronized at the various stages of the cell cycle .
The cell cycle dependence of the Tat response was confirmed by inhibiting G0 --&gt; G1 progression with the expression of dominant negative mutant Ras( Asn17 ) or the cyclin - dependent kinase CDK4 .
The results support a mechanism whereby transactivation of the HIV promoter is regulated by cell growth signal transduction pathways that target the Tat cofactor .
******************************* END ABSTRACT 10639311 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_66'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 8900533 ***********************************************
Role of IgE immune complexes in the regulation of HIV-1 replication and increased cell death of infected U1 monocytes : involvement of CD23 / Fc epsilon RII- mediated nitric oxide and cyclic AMP pathways .
BACKGROUND : IgE /anti-IgE immune complexes ( IgE-IC ) induce the release of multiple mediators from monocytes / macrophages and the monocytic cell line U937 following the ligation of the low - affinity Fc epsilon receptors ( Fc epsilon RII / CD23 ) .
These effects are mediated through an accumulation of cAMP and the generation of L-arginine - dependent nitric oxide ( NO ) .
Since high IgE levels predict more rapid progression to acquired immunodeficiency syndrome , we attempted to define the effects of IgE-IC on human immunodeficiency virus ( HIV ) production in monocytes .
MATERIALS AND METHODS : Two variants of HIV-1 chronically infected monocytic U1 cells were stimulated with IgE-IC and virus replication was quantified .
NO and cAMP involvement was tested through the use of agonistic and antagonistic chemicals of these two pathways .

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

This effect was due to the crosslinking of CD23 , as it was reversed by blocking the IgE binding site on CD23 .
The IgE-IC effect could also be mimicked by crosslinking of CD23 by a specific monoclonal antibody .
p24 induction by IgE-IC was then shown to be due to CD23 - mediated stimulation of cAMP , NO , and tumor necrosis factor alpha ( TNF alpha ) generation .
In another variant of U1 cells with &gt; 1 log higher constitutive production of p24 levels ( U1high ) , IgE-IC addition dramatically decreased all cell functions tested and accelerated cell death .
This phenomenon was reversed by blocking the nitric oxide generation .
CONCLUSIONS : These data point out a regulatory role of IgE-IC on HIV-1 production in monocytic cells , through CD23 - mediated stimulation of cAMP and NO pathways .
IgE-IC can also stimulate increased cell death in high HIV producing cells through the NO pathway .
******************************* END ABSTRACT 8900533 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_106'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 15746253 ***********************************************
SRC homology-2- containing protein tyrosine phosphatase-1 restrains cell proliferation in human medullary thyroid carcinoma .
Medullary thyroid carcinoma ( MTC ) is a rare tumor originating from thyroid parafollicular C cells , where , in the inherited form , constitutive activation of the RET protooncogene is responsible for unrestrained cell proliferation .
We previously demonstrated that somatostatin ( SRIF ) reduces cell growth in the human MTC cell line TT , which expresses all SRIF receptor ( SSTR ) subtypes and responds differently to selective SSTR agonists .
The antiproliferative mechanism of SRIF and its analogs in MTC is still unclear .
Src homology-2- containing protein tyrosine phosphatase-1 ( SHP-1 ) , a cytoplasmic protein tyrosine phosphatase ( PTP ) , is activated by somatotropin release - inhibiting factor and reduces mutated RET autophosphorylation in a heterologous system .
In this study , we explore the role of PTP activation , in particular of SHP-1 , in TT cells , where RET is constitutively activated .

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

<xsl:when test="@id='word_132'">

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

Blockade of PTP activity with sodium orthovanadate induced cell proliferation and MAPK phosphorylation and blunted the inhibitory effects of SRIF .
Moreover , SHP-1 associates with SSTR2 depending on its activation .
By using a MAPK kinase inhibitor , we demonstrated that TT cell growth depends on MAPK pathway activation .
Furthermore , in TT cells overexpressing SHP-1 , cell proliferation and MAPK signaling were strongly down - regulated , whereas in TT cells transfected with a dominant negative form of SHP-1 , cell proliferation and MAPK signaling were markedly induced .
Our data demonstrate that SRIF inhibitory effects on TT cell proliferation are mediated , at least in part , by SHP-1 , which acts through a MAPK - dependent mechanism .
******************************* END ABSTRACT 15746253 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_133'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11101689 ***********************************************
Expression and characterization of the human YWK-II gene , encoding a sperm membrane protein related to the alzheimer betaA4 -amyloid precursorprotein .
The YWK-II cDNA , RSD-2 , encoding a sperm membrane protein was isolated from a rat testis cDNA expression library .
Using the RSD-2 insert in combination with rapid amplification of cDNA ends ( RACE ) , the corresponding human gene was isolated from a human testis cDNA expression library .
The human testis cDNA , HSD-2 , is 3654 bp in length and contains an open reading frame of 763 codons .

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

<xsl:when test="@id='word_146'">

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

The deduced polypeptide has partial homology with the amyloid precursor protein ( APP ) and high homology with the amyloid precursor homologue , APLP2 / APPH .
The YWK-II gene was mapped and assigned to human chromosome locus : 11q24-25 .
Northern blotting of various human tissue RNAs using the HSD-2 cDNA as a probe showed that the gene is transcribed ubiquitously .
The cytoplasmic domain of HSD-2 was expressed in Escherichia coli .
In -vitro studies showed that the recombinant polypeptide bound to a GTP- binding protein ( G(o) ) and was phosphorylated by protein kinase C and cdc2 kinase .
In mammalian F11 cells , the recombinant polypeptide was found to be coupled to G(o ) .
Thus , the YWK-II component has the characteristics of a G(o)-coupled receptor and may be involved in G(o) - mediated signal transduction pathway .
Protein kinase C and cdc2 kinase may regulate this pathway in spermatozoa by phosphorylating the cytoplasmic domain of the YWK-II component .
******************************* END ABSTRACT 11101689 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_147'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 14572900 ***********************************************
Endothelial stress by gravitational unloading : effects on cell growth and cytoskeletal organization .
All organisms on Earth have evolved to survive within the pull of gravity .
Orbital space flights have clearly demonstrated that the absence or the reduction of gravity profoundly affects eukaryotic organisms , including man .

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

<xsl:when test="@id='word_172'">

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

cardiovascular deconditioning has been described in astronauts , we evaluated whether microgravity affected endothelial functions .
We show that microgravity reversibly stimulated endothelial cell growth .
This effect correlated with an overexpression of heat shock protein 70 ( hsp70 ) and a down - regulation of interleukin 1 alpha ( IL-1alpha ) , a potent inhibitor of endothelial cell growth , also implicated in promoting senescence .
In addition , gravitationally unloaded endothelial cells rapidly remodelled their cytoskeleton and , after a few days , markedly down - regulated actin through a transcriptional mechanism .
We hypothesize that the reduction in the amounts of actin in response to microgravity represents an adaptative mechanism to avoid the accumulation of redundant actin fibers .
******************************* END ABSTRACT 14572900 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_173'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 8887661 ***********************************************
Interferon regulatory factors and TFIIB cooperatively regulate interferon - responsive promoter activity in vivo and in vitro .
Interferon regulatory factors ( IRFs ) bind to the interferon - stimulated response element ( ISRE ) and regulate interferon - and virus - mediated gene expression .
IRF-1 acts as a transcriptional activator , while IRF-2 acts as a repressor .
Here we show that IRF-1 and IRF-2 bind to both cellular TFIIB , a component of the basal transcription machinery , and recombinant TFIIB ( rTFIIB ) and that this protein - protein interaction facilitates binding of IRFs to the ISRE .
A functional interaction between TFIIB and IRF was assessed by a newly established in vitro transcription assay in which recombinant IRF-1 ( rIRF-1 ) stimulated transcription specifically from an ISRE -containing template .
With this assay we show that rIRF-1 and rTFIIB cooperatively enhance the ISRE promoter in vitro .

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

<xsl:when test="@id='word_205'">

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

The cooperative enhancement by TFIIB and IRF-1 was independent of the TATA sequence in the ISRE promoter but dependent on the initiator sequence ( Inr ) and was abolished when P19 cells were induced to differentiate by retinoic acid treatment .
In contrast , cotransfection of TFIIB and IRF-1 into NIH 3T3 cells resulted in a dose - dependent repression of promoter activation which occurred in a TATA - dependent manner .
Our results indicate the presence of a cell type - specific factor that mediates the functional interaction between IRFs and TFIIB and that acts in conjunction with the requirement of TATA and Inr for promoter activation .
******************************* END ABSTRACT 8887661 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_206'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11559949 ***********************************************
Modulation of MEK activity during G-CSF signaling alters proliferative versus differentiative balancing .
Previous studies of the granulocyte colony stimulating factor ( G-CSF ) receptor have demonstrated that discrete signals direct proliferative and maturation signaling .
Receptor deletion / mutant studies have shown that although activation of the ras - mitogen activated protein ( MAP ) kinase pathway is necessary for G-CSF directed proliferation , it is not necessary for maturation induced by this cytokine .

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

<xsl:when test="@id='word_235'">

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

Using the human G-CSF responsive MPD cell line , we specifically inhibited MEK using PD 98059 and also transfected MPD cells with a constitutively active MEK construct .
We then exposed the cells to G-CSF and assessed the effects of MEK inhibition and forced expression on proliferation and differentiation .
Inhibition of MEK followed by G-CSF stimulation consistently resulted in an early 2.5 - fold increase in morphologically differentiated neutrophils expressing CD11b and CD16 and containing lactoferrin over that produced by G-CSF alone .
MEK inhibition alone had little effect on the differentiation stage of these cells , although proliferation was impaired .
Forced expression of activated MEK resulted in a three - to five - fold decrease in differentiated , lactoferrin containing neutrophilic cells resultant from G-CSF induction , and a commensurate increase in cell proliferation .
These observations suggest that modulation of MAPK activation may be a control point for altering the balance between proliferation and differentiation in response to G-CSF .
Physiologically , this control is likely exerted by costimulatory cytokines .
******************************* END ABSTRACT 11559949 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_236'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9931297 ***********************************************
Cell -cycle - regulated phosphorylation of cAMP response element - binding protein : identification of novel phosphorylation sites .
We report that the cAMP response element binding protein ( CREB ) undergoes cell - cycle - regulated phosphorylation .
In human amnion FL cells , CREB was expressed as two forms with different molecular masses , 45 and 45.5 kDa .
Although asynchronous cells contained predominantly the 45 kDa forms , this form shifted to 45.5 kDa when the cells were synchronized with the early S-phase .
Furthermore the expression of the 45.5 kDa band was increased when cells were treated with okadaic acid , confirming that the 45.5 kDa band was a phosphorylated form of the 45 kDa band .

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

<xsl:when test="@id='word_303'">

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

A mutant in which Ser111 and Ser114 were each replaced by a glutamic residue , mimicking a phosphorylated state , had a higher activation potential in cAMP response element - mediated transcription .
These results strongly suggest that the casein kinase II target region is involved in cell cycle - regulated phosphorylation of the CREB protein and also in transcriptional enhancement .
******************************* END ABSTRACT 9931297 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_304'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 12674495 ***********************************************
3'UTRs of glutathione peroxidases differentially affect selenium - dependent mRNA stability and selenocysteine incorporation efficiency .
Selenoprotein mRNAs are particular in several aspects .

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

<xsl:when test="@id='word_347'">

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

In the familiy of selenium - dependent glutathione peroxidases ( GPx ) the ranking is GI- GPx &gt; or = PHGPx &gt; cGPx = pGPx .
This phenomenon was studied by mutually combining the coding regions of GI- GPx , PHGPx and cGPx with their 3'UTRs .
HepG2 cells were stably transfected with the resulting constructs .
Expression of glutathione peroxidases was estimated by activity measurement and Western blotting , the selenium - dependent mRNA stability by real -time PCR .
Whereas 3'UTRs from stable PHGPx and GI- GPx could be exchanged without loss of stability , they were not able to stabilize cGPx mRNA .
cGPx 3'UTR rendered GI- GPx and PHGPx mRNA unstable .
Thus , cGPx mRNA contains selenium - responsive instability elements in both the translated and the untranslated region , which cannot be compensated by one of the stable homologs .
Stabilizing efficiency of an individual GPx 3'UTR did not correlate with the efficiency of selenocysteine incorporation .
PHGPx 3'UTR was equally effective as cGPx 3'UTR in enhancing GPx activity in all constructs , while GI- GPx 3'UTR showed a markedly lower efficacy .
We conclude that different mRNA sequences and/or RNA - binding proteins might regulate mRNA stability and translation of selenoprotein mRNA .
******************************* END ABSTRACT 12674495 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_348'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 15215887 ***********************************************
Functional improvement of mutant keratin cells on addition of desmin : an alternative approach to gene therapy for dominant diseases .
A major challenge to the concept of gene therapy for dominant disorders is the silencing or repairing of the mutant allele .

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

<xsl:when test="@id='word_383'">

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

Epidermolysis bullosa simplex ( EBS ) is a genetic skin fragility disorder caused by mutations in the genes for keratins K5 or K14 , the intermediate filaments present in the basal cells of the epidermis .
Keratin diseases are nearly all dominant in their inheritance .
In cultured keratinocytes , mutant keratin renders cells more sensitive to a variety of stress stimuli such as osmotic shock , heat shock or scratch wounding .
Using a 'severe ' disease cell culture model system , we demonstrate reversion towards wild - type responses to stress after transfection with human desmin , an intermediate filament protein normally expressed in muscle cells .
Such a supplementation therapy approach could be widely applicable to patients with related individual mutations and would avoid some of the financial obstacles to gene therapy for rare diseases .
******************************* END ABSTRACT 15215887 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_384'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9430691 ***********************************************
Intercellular calcium signaling via gap junction in connexin- 43-transfected cells .
In excitable cells , intracellular Ca2 + is released via the ryanodine receptor from the intracellular Ca2 + storing structure , the sarcoplasmic reticulum .
To determine whether this released Ca2 + propagates through gap junctions to neighboring cells and thereby constitutes a long range signaling network , we developed a cell system in which cells expressing both connexin- 43 and ryanodine receptor are surrounded by cells expressing only connexin- 43 .

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

<xsl:when test="@id='word_419'">

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

Inhibitors of gap junctional communication rapidly and reversibly abolished this propagation of Ca2+ .
Together with the electrophysiological analysis of transfected cells , the observed intercellular Ca2 + wave was revealed to be due to the reconstituted gap junction of transfected cells .
We next evaluated the functional roles of cysteine residues in the extracellular loops of connexin- 43 in gap junctional communication .
Mutations of Cys54 , Cys187 , Cys192 , and Cys198 to Ser showed the failure of Ca2 + propagation to neighboring cells in accordance with the electrical uncoupling between transfected cells , whereas mutations of Cys61 and Cys68 to Ser showed the same pattern as the wild type .
[14C]Iodoacetamide labeling of free thiols of cysteine residues in mutant connexin- 43s showed that two pairs of intramolecular disulfide bonds are formed between Cys54 and Cys192 and between Cys187 and Cys198 .
These results suggest that intercellular Ca2 + signaling takes place in cultured cells expressing connexin- 43 , leading to their own synchronization and that the extracellular disulfide bonds of connexin- 43 are crucial for this process .
******************************* END ABSTRACT 9430691 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_420'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10637504 ***********************************************
A role for PML and the nuclear body in genomic stability .
The PML gene of acute promyelocytic leukemia ( APL ) encodes a cell - growth and tumor suppressor .

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

<xsl:when test="@id='word_435'">

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

The Bloom syndrome gene BLM encodes a RecQ DNA helicase , whose absence from the cell results in genomic instability epitomized by high levels of sister - chromatid exchange ( SCE ) and cancer predisposition .
We show here that BLM co - localizes with PML to the NB .
In cells from persons with Bloom syndrome the localization of PML is unperturbed , whereas in APL cells carrying the PML-RARalpha oncoprotein , both PML and BLM are delocalized from the NB into microspeckled nuclear regions .
Treatment with retinoic acid ( RA ) induces the relocalization of both proteins to the NB .
In primary PML -/- cells , BLM fails to accumulate in the NB .
Strikingly , in PML -/- cells the frequency of SCEs is increased relative to PML + / + cells .
These data demonstrate that BLM is a constituent of the NB and that PML is required for its accumulation in these nuclear domains and for the normal function of BLM .
Thus , our findings suggest a role for BLM in APL pathogenesis and implicate the PML NB in the maintenance of genomic stability .
******************************* END ABSTRACT 10637504 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_436'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9990121 ***********************************************
Cellular and functional characterization of three recombinant antithrombin mutants that caused pleiotropic effect - type deficiency .

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

<xsl:when test="@id='word_449'">

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

Pleiotropic effect - type mutants of antithrombin that have an amino acid replacement in a distal hinge region including strands 1C , 4B , and 5B of the polypeptide chain are known to exhibit impaired interactions with both thrombin and heparin , coupled with a secretion defect .
To examine the mechanism of pleiotropic effect - type antithrombin deficiency , we expressed three mutants , Oslo ( Ala404 --&gt; Thr ) , Kyoto ( Arg406 --&gt; Met ) , and Utah ( Pro407 --&gt; Leu ) , in baby hamster kidney ( BHK ) cells , and compared their secretion rates , affinities for heparin and abilities to form thrombin - antithrombin ( TAT ) complexes with those of wild - type ( Wt ) antithrombin .
Pulse - chase experiments showed that the Oslo - and Kyoto - mutants were secreted at rates similar to Wt antithrombin .
In contrast , the Utah - mutant underwent partial intracellular degradation .
The intracellular degradation of the Utah - mutant was not inhibited by lysosomotropic inhibitors , but by proteasome inhibitors such as carbobenzoxy-L-leucyl-L-leucyl-L-leucinal ( LLL ) and lactacystin , indicating that a part of the Utah - mutant was degraded by proteasome through quality control in the endoplasmic reticulum ( ER ) .
Crossed immunoelectrophoresis in the presence of heparin showed that only the Oslo- mutant lacks heparin - binding ability .
Incubation with thrombin showed that the Kyoto - and Utah - mutants , but not the Oslo- mutant , formed a weak but detectable TAT complex .
Furthermore , heparin enhanced the TAT complex formation by the Kyoto - and Utah - mutants , suggesting heparin cofactor activities of these mutants .
These results show that each of the Oslo - , Kyoto - , and Utah - mutants exhibits different properties as to secretion , intracellular degradation and functional activity , although they are grouped as pleiotropic effect - type mutants .
******************************* END ABSTRACT 9990121 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_450'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 7588595 ***********************************************
Inhibition of adipogenesis by the stress - induced protein CHOP ( Gadd153 ) .
Adipocytic conversion of 3T3-L1 cells is dependent on induction of transcription factors from the C/ EBP family that activate promoters of adipogenic genes .
We find that expression of CHOP , a nuclear protein that dimerizes avidly with C/ EBP isoforms alpha and beta and directs the resulting heterodimer away from classic C/ EBP - binding sites , markedly inhibits this differentiation process .

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

<xsl:when test="@id='word_469'">

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

Ectopic expression of C/ EBP alpha bypasses the inhibitory effect of CHOP on differentiation , providing further evidence that CHOP action is mediated by inhibition of C/ EBP alpha gene expression rather than merely inhibiting the encoded protein 's DNA - binding activity .
A similar pattern of attenuated expression of C/ EBP alpha and beta is also observed in cells induced to differentiate in media with low glucose concentration .
This stressed culture condition is associated with induction of endogenous CHOP and marked attenuation of the differentiation process .
Our data suggest that CHOP functions as an inducible inhibitor of adipocytic differentiation in response to metabolic stress .
It does so by interfering with the accumulation of adipogenic C/ EBP isoforms .
******************************* END ABSTRACT 7588595 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_470'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9990007 ***********************************************
Two differently regulated nuclear factor kappaB activation pathways triggered by the cytoplasmic tail of CD40 .
CD40 signaling modulates the immune response at least in part by activation of nuclear factor kappaB ( NFkappaB ) .

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

<xsl:when test="@id='word_498'">

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

Although four members of the tumor necrosis factor receptor - associated factor ( TRAF ) family , including TRAF2 , TRAF3 , TRAF5 , and TRAF6 , bind to the CD40 cyt , how each TRAF protein contributes to the NFkappaB activation by CD40 is not clear .
Here we report that TRAF2 , TRAF3 , and TRAF5 bind cyt - C , whereas TRAF6 binds cyt-N.
cyt- N is conserved poorly between human and mouse CD40 , while cyt - C is highly conserved .
However , single aa substitution of Glu- 235 in cyt- N of human CD40 with Ala abolishes the binding of TRAF6 to cyt- N and NFkappaB activation by cyt-N.
Conservation of this Glu between mouse and human CD40 strongly suggests that TRAF6 could link cyt- N to signals essential for CD40 - mediated immune response .
Furthermore , NFkappaB activation by cyt - C is inhibited by a kinase - negative form of NFkappaB - inducing kinase more efficiently than that by cyt-N , consistent with the result that NFkappaB activation by TRAF2 and TRAF5 is inhibited by a kinase - negative form of NFkappaB - inducing kinase more efficiently than that by TRAF6 .
These results indicate that NFkappaB activating signals emanating from cyt- N and cyt - C are mediated by the different members of the TRAF family and could be regulated in a distinct manner .
******************************* END ABSTRACT 9990007 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_499'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 12673675 ***********************************************
Expression of hypoxia - inducible factor- 1alpha is associated with tumor vascularization in human colorectal carcinoma .
HIF-1 is reported to transactivate expression of VEGF , which is an important angiogenic factor .
To determine whether HIF-1alpha plays a role in angiogenesis through its regulation of VEGF , we examined expression of HIF-1alpha and its relation to clinicopathologic features , VEGF expression and prognosis of patients with colorectal carcinoma .
Expression of HIF-1alpha and VEGF was examined in 4 colorectal carcinoma cell lines ( COLO320DM , COLO201 , DLD-1 , WiDr ) and 149 colorectal carcinoma tissues ( 10 fresh specimens , 139 archival , paraffin - embedded specimens ) .

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

<xsl:when test="@id='word_536'">

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

In 8 of 10 patients with colorectal cancer , expression of HIF-1alpha and VEGF was increased in tumor tissues compared to corresponding normal mucosa .
Of 139 archival specimens of colorectal carcinoma , 81 ( 58.3 % ) expressed HIF-1alpha protein at a high level .
HIF-1alpha expression was correlated with tumor invasion , tumor stage , lymphatic invasion , venous invasion and liver metastasis .
Moreover , HIF-1alpha expression was correlated significantly with VEGF expression and microvessel density .
Although there was a tendency for poorer prognosis in patients with high HIF-1alpha- expressing tumors , this correlation was not statistically significant .
These findings suggest that HIF-1alpha may play a role in angiogenesis and tumor progression via regulation of VEGF in human colorectal carcinoma .
******************************* END ABSTRACT 12673675 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_537'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 15201983 ***********************************************
8- Chloro- adenosine sensitizes a human hepatoma cell line to TRAIL - induced apoptosis by caspase - dependent and - independent pathways .

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

<xsl:when test="@id='word_578'">

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

8- Chloro- adenosine ( 8-Cl - Ado ) is a potential anti - cancer chemical agent now in clinical trail phase II , though its molecular mechanism remains poorly understood .
In the present study , we report that 8-Cl - Ado can promote TRAIL killing activity in the hepatoma cell line BEL-7402 in dose - and time - dependent manner when jointly used in vitro .
We showed that the expression of death receptor DR5 , but not DR4 was up -regulated and the decoy receptor DcR1 was down - regulated in the cells treated with 8-Cl - Ado and the recombinant soluble TRAIL ( rsTRAIL , 95 -281 a.a. ) .
Further experiments demonstrated that caspase - family inhibitor z-VAD - fmk prevented the cells from apoptosis induced by co - treatment with 8-Cl - Ado and rsTRAIL for 6 h , however , apoptosis occurred in the cells cultured for 24 h , suggesting that co - treatment induce a caspase - dependent and - independent signaling pathway in the BEL-7402 cells .
This phenomenon was confirmed by cleavage analysis of caspase-3 and poly(ADP-ribose ) polymerase ( PARP ) , and ROS ( reactive oxygen species ) assay , respectively .
Moreover , transcriptional activity test showed that NF-kappaB was inhibited in the BEL-7402 cells during co - treatment .
Our results provided evidence for the first time that 8-Cl - Ado sensitizes the human hepatoma cells BEL-7402 to rsTRAIL - induced apoptosis by up -regulating DR5 expression , inactivating the NF-kappaB activity , and signaling by the caspase - dependent and - independent pathway .
******************************* END ABSTRACT 15201983 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_579'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11103796 ***********************************************
Arsenite induces p53 accumulation through an ATM - dependent pathway in human fibroblasts .
Arsenic compounds are potent human carcinogens .
Accumulated evidence has shown that arsenite - induced cytogenetic alterations are associated with the carcinogenicity of arsenic .
Because p53 plays a guarding role in maintaining genome integrity and accuracy of chromosome segregation , the mechanistic effects of arsenite on p53 activation were analyzed .

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

<xsl:when test="@id='word_610'">

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

Accompanying the appearance of DNA strand breaks was a significant accumulation of p53 in arsenite - treated HFW cells , as demonstrated by immunoblotting and immunofluorescence techniques .
p53 downstream proteins , such as p21 and the human homologue of murine double minute -2 , were also significantly induced by arsenite treatment .
Cell cycle retardation and G2- M arrest were observed in 5-bromo-2'-deoxyuridine pulse - labeled HFW cells by flow cytometry .
Wortmannin , an inhibitor of phosphatidylinositol 3-kinases , inhibited arsenite - or X -ray irradiation - induced p53 accumulation but did not alter UV irradiation - or N-acetyl- Leu-Leu-norleucinal - induced p53 accumulation .
p53 phosphorylation on serine 15 was also confirmed by immunoblotting technique in arsenite - and X -ray - treated HFW cells but was not observed in UV - or N-acetyl- Leu-Leu-norleucinal - treated HFW cells .
These results suggest the involvement of a phosphatidylinositol 3-kinase - related protein kinase in arsenite - induced p53 accumulation .
For confirmation , we demonstrated that arsenite treatment , similar to X -ray irradiation , did not induce p53 accumulation in GM3395 fibroblasts derived from a patient with ataxia telangiectasia .
In contrast , UV irradiation did cause p53 accumulation in these cells .
Together , these findings infer that arsenite - induced DNA strand breaks may lead to p53 phosphorylation and accumulation through an ataxia telangiectasia mutated - dependent pathway in HFW cells .
******************************* END ABSTRACT 11103796 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_611'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11559707 ***********************************************
Silencing mediator of retinoid and thyroid hormone receptors and activating signal cointegrator-2 as transcriptional coregulators of the orphan nuclear receptor Nur77 .
For the orphan nuclear receptor subfamily that includes Nur77 ( NGFI-B ) , Nurr1 , and NOR-1 , no transcriptional coregulators have been identified thus far .
In this report , we found that Ca(2+) / calmodulin - dependent protein kinase IV enhances Nur77 transactivation in cotransfections either alone or in synergy with AF2dependent coactivator ASC-2 , whereas corepressor silencing mediator for retinoid and thyroid hormone receptors ( SMRT ) is repressive .
Interestingly , Nur77 interacted with SMRT but did not directly bind ASC-2 , and accordingly , the putative AF2 core domain of Nur77 did not affect the Nur77 transactivation .
SMRT harbors transferable repression domains that associate with various histone deacetylases .
Surprisingly , histone deacetylase inhibitor trichostatin A was unable to block the repressive effect of SMRT while dramatically stimulating the Nur77 transactivation .

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

<xsl:when test="@id='word_650'">

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

******************************* END ABSTRACT 11559707 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_651'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11533036 ***********************************************
Phosphorylation and cell cycle - dependent regulation of Na+ /H + exchanger regulatory factor-1 by Cdc2 kinase .
Na(+) / H(+ ) exchanger regulatory factor ( NHERF )-1 is a PDZ domain - containing adaptor protein known to bind to various receptors , channels , cytoskeletal elements , and cytoplasmic signaling proteins .
We report here that the phosphorylation state of NHERF-1 is profoundly regulated by the cell cycle : NHERF-1 in HeLa cells is hyperphosphorylated in mitosis phase and much less phosphorylated at other points of the cell cycle .
This mitosis phase - dependent phosphorylation of NHERF-1 could be blocked by roscovitine , consistent with phosphorylation by cyclin - dependent kinases .

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

<xsl:when test="@id='word_675'">

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

In contrast , the NHERF-1 relative NHERF-2 was not phosphorylated at all by Cdc2 .
NHERF-1 possesses two serines ( Ser( 279 ) and Ser( 301 ) ) that conform to the SPX( K/R ) motif preferred for phosphorylation by Cdc2 .
Mutation of either of these serines reduced Cdc2 - mediated phosphorylation of NHERF-1 in vitro , and mutation of both residues together completely abolished Cdc2 - mediated phosphorylation .
When the S279A / S301A NHERF-1 mutant was expressed in cells , it failed to exhibit the mitosis phase - dependent phosphorylation observed with wild - type NHERF-1 .
Mutation of both Ser( 279 ) and Ser( 301 ) to aspartate , to mimic Cdc2 phosphorylation of NHERF-1 , resulted in a NHERF-1 mutant with a markedly impaired ability to oligomerize in vitro .
Similarly , endogenous NHERF-1 from lysates of mitosis phase HeLa cells exhibited a markedly reduced ability to oligomerize relative to endogenous NHERF-1 from lysates of interphase HeLa cells .
Mitosis phase NHERF-1 furthermore exhibited the ability to associate with Pin1 , a WW domain - containing peptidylprolyl isomerase that does not detectably bind to NHERF-1 in interphase lysates .
The association of NHERF-1 with Pin1 facilitated dephosphorylation of NHERF-1 , as shown in experiments in which cellular Pin1 activity was blocked by the selective inhibitor juglone .
These data reveal that cellular NHERF-1 is phosphorylated during mitosis phase by Cdc2 at Ser( 279 ) and Ser( 301 ) and that this phosphorylation regulates NHERF-1 oligomerization and association with Pin1 .
******************************* END ABSTRACT 11533036 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_676'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10656451 ***********************************************
Drug - induced apoptosis in lung cnacer cells is not mediated by the Fas / FasL ( CD95 / APO1 ) signaling pathway .
Anticancer drugs exert at least part of their cytotoxic effect by triggering apoptosis .
We previously identified chemotherapy - induced apoptosis in lung cancer cells and suggested a role for p53 alternative or complementary pathways in this process .
Recently , a role for the Fas / FasL ( CD95 / Apo1 ) signaling system in chemotherapy - induced apoptosis was proposed in some cell types .

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

<xsl:when test="@id='word_716'">

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

We assessed the expression of Fas and FasL and the function of the Fas pathway in six lung cancer cell lines ( H460 , H322 , GLC4 , GLC4 / ADR , H187 , and N417 ) .
All lung cancer cell lines expressed Fas and FasL at RNA and protein levels , and apoptosis could be induced in four of six cell lines upon exposure to the Fas agonistic monoclonal antibody ( mAb ) CLB-CD95 / 15 .
Nevertheless , after drug exposure , no significant FasL up -regulation was observed , whereas the Fas expression was increased in the wild - type p53 cell line H460 , but not in the other lines , proved to be mutant p53 by direct gene sequencing .
Moreover , no correlation was observed in lung cancer cell lines between sensitivity to drugs and to a Fas agonistic mAb , and preincubation of cells with either the Fas -antagonistic mAb CLB-CD95 /2 or a FasL-neutralizing mAb did not protect from drug - induced apoptosis .
Taken together , these observations strongly argue against a role of the Fas / FasL signaling pathway in drug - induced apoptosis in lung cancer cells .
Interestingly , caspase - 8 activation was observed upon drug exposure , independently from Fas / FasL signaling .
******************************* END ABSTRACT 10656451 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_717'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9931461 ***********************************************
Structure of the gene encoding the ubiquitin - conjugating enzyme Ubcm4 , characterization of its promoter , and chromosomal location .
Ubiquitin - conjugating enzymes ( E2 or Ubc ) play a key role in the post - translational modification of proteins by ubiquitylation .
They are encoded by a large family of genes that are closely related to each other .

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

At the genomic level the Ubcm4 gene spans approx .
50kb and is composed of four exons .
Only about 1 % of the total gene codes for amino acids .
The four different Ubcm4 specific RNAs encode the same protein and differ only in the length of the 3 ' untranslated region .
The polyadenylation signals used by the four different RNAs are all within the 3 ' terminal exon .
At the 5 ' end of the gene , multiple transcriptional start sites were mapped within a region of 25bp.
The region proximal to the initiation sites does not contain a TATA box and is not GC -rich .
Transient chloramphenicol acetyltransferase assays , however , showed that this region can promote the expression of a reporter gene and that 15bp upstream of the first initiation site were sufficient for basal expression .
The Ubcm4 gene was mapped by interspecific backcross analysis to the proximal region of mouse chromosome 16 .
******************************* END ABSTRACT 9931461 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_748'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 9930302 ***********************************************
Liposomes enhance delivery and expression of an RGD - oligolysine gene transfer vector in human tracheal cells .

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

<xsl:when test="@id='word_762'">

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

We examined integrin - mediated gene delivery using an Arg - Gly- Asp/oligo-L-lysine ( [K]16RGD ) cyclic peptide and investigated its gene transfer efficiency when associated with a cationic liposome .
We demonstrated that human cystic fibrosis and noncystic fibrosis tracheal epithelial cells in culture express integrins that recognise the RGD integrin - binding motif .
We found a 10 - fold ( P &lt; 0.01 ) increased expression of a luciferase encoding plasmid in these cells when complexing the plasmid to the [ K]16RGD peptide as compared with plasmid alone .
This increase was specific to the [ K]16RGD peptide since neither a [ K]16RGE nor a [ K]16 peptide gave a comparable increase .
Expression was further enhanced 30 - fold ( P &lt; 0.01 ) with lipofectamine and the ratio of DNA / peptide / lipofectamine was critical for specificity and expression .
Fluorescence and radioactive labelling of the complex showed that the [ K]16RGD peptide increased the endocytic uptake of DNA into cells .
The cell association of both DNA and peptide increased even further with lipofectamine .
Confocal microscopy showed that the [ K]16RGD peptide and the DNA internalised together within 30 min and localised to vesicles in the perinuclear region .
These results show that an integrin - binding ligand can deliver genetic material to airway cells and that a cationic liposome can enhance the efficacy of this nonviral vector system .
******************************* END ABSTRACT 9930302 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_763'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 1640731 ***********************************************
Impaired degradation of Ca(2+) - regulating second messengers in myeloid leukemia cells .

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

<xsl:when test="@id='word_771'">

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

Inositol 1,4,5-trisphosphate and inositol 1,3,4,5-tetrakisphosphate are Ca(2+) - regulating second messenger molecules which are generated via the cleavage of inositol lipids .
We have previously shown that these species are autonomously generated in HL60 myeloid leukemia cells and that they may play a role in signalling the continuous proliferation of this cell line .
Here we show that the activity of the 5-phosphomonoesterase ( 5- PME ) enzyme which cleaves and inactivates these second messengers was strikingly reduced in HL60 cells compared to normal granulocytes or macrophages .
Induction of differentiation of HL60 cells along the monocyte / macrophage or granulocytic pathways did not result in a significant increase in 5-PME activity .
The activity of this enzyme was also low in extracts of bone marrow mononuclear cells from four patients with myeloid leukemia .
A lesion in the 5-PME pathway may therefore result in the conservation of Ca(2+) - regulating second messengers in the HL60 cell line and in some myeloid leukemia cells .
It is plausible that this lesion may co - operate with the autonomous cleavage of inositol lipids in the signalling of leukemic cell proliferation .
******************************* END ABSTRACT 1640731 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_772'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10656686 ***********************************************
p95(vav) associates with the type I interferon ( IFN ) receptor and contributes to the antiproliferative effect of IFN-alpha in megakaryocytic cell lines .
The vav proto - oncogene product is a 95 kDa protein predominantly expressed in hematopoietic cells .
Vav presents a wide range of functional domains , including structural domains known to be involved in signal transduction .

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

<xsl:when test="@id='word_793'">

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

Nevertheless , the biological functions of p95(vav) are still unclear .
This report is the first documentation on the physical association of p95(vav) with both alpha and beta type I interferon receptor chains , as demonstrated by co - immunoprecipitation and Western blot analysis in megakaryocytic cells ( Dami and UT7 ) .
This interaction is increased by interferon -alpha / beta stimulation .
Moreover , p95(vav) phosphorylated subsequently to type I interferon treatment , is translocated in the nucleus ; a concomitant increase of its association with the regulatory subunit of the nuclear DNA - dependent protein kinase , KU-70 is observed in the nucleus .
To determine whether p95(vav) participates in the biological response to type I interferons , we studied the effects of non modified Vav oligodeoxynucleotides on the antiproliferative effect of interferon -alpha on megakaryocytic cells .
By this oligodeoxynucleotide strategy , we show that p95(vav) contributes greatly to the cell proliferation inhibition induced by type I IFN .
******************************* END ABSTRACT 10656686 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_794'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11544337 ***********************************************
IL-3 induces down - regulation of CCR3 protein and mRNA in human eosinophils .
Cytokines and chemokines are responsible for the attraction and activation of eosinophils in allergic and inflammatory diseases .

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

<xsl:when test="@id='word_842'">

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

Recent studies have demonstrated the importance of CCR3 on human eosinophils that undergo receptor recycling after chemokine activation , but the modulation of this receptor by cytokines has not yet been addressed .
In this study , we demonstrate that IL-3 induces a dose - and time - dependent down - regulation of CCR3 from the surface of human eosinophils comparable to the CCR3 - specific ligand eotaxin , whereas IL-5 , GM -CSF , IL-4 , IL-10 , IL-13 , IFN-gamma , and TNF-alpha had no effect .
Maximal down - regulation of CCR3 in response to IL-3 was reached at 24 h .
Reduction of CCR3 surface protein in response to IL-3 could be prevented by an anti - IL-3 mAb and was neither due to the release of CC chemokines nor to nonspecific binding of IL-3 to CCR3 .
Moreover , down - regulation was prevented by phenylarsine oxide , a nonspecific inhibitor of receptor internalization .
After 24 h , IL-3 - induced decrease of CCR3 surface expression correlated with diminished mRNA expression , suggesting a transcriptional regulation mechanism .
Since wortmannin partially inhibited IL-3 - but not eotaxin - induced CCR3 down - regulation , receptor down - modulation seems to underlie different signaling events .
Therefore , these data suggest a novel role for the cytokine IL-3 in the activation process of eosinophils and its predominant chemokine receptor CCR3 .
******************************* END ABSTRACT 11544337 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_843'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 8290273 ***********************************************
Novel human oncogene lbc detected by transfection with distinct homology regions to signal transduction products .
In order to isolate transforming genes involved in leukemias , DNA from a CML acute phase sample was transfected into NIH-3T3 cells and found to be tumorigenic in nude mice .
Partial genomic cloning using human repeat sequence as probe followed by cDNA cloning of this oncogene , termed lbc , was undertaken .
The lbc cDNA sequence shows no identity to known proteins and codes for a predicted hydrophilic protein product of 47 kD , which contains several consensus kinase phosphorylation sites .

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

<xsl:when test="@id='word_902'">

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

Lbc expression is restricted to human hematopoietic cells and skeletal muscle , lung and heart .
Transfection of 3T3 cells with an expression vector encoding lbc cDNA results in focus formation , demonstrating its biological activity .
These data indicate that the lbc oncogene encodes a novel product implicated in distinct cellular signal transduction functions .
******************************* END ABSTRACT 8290273 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_903'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 11532855 ***********************************************
OGG1 protein suppresses G :C--&gt; T :A mutation in a shuttle vector containing 8-hydroxyguanine in human cells .
8-Hydroxyguanine ( 8-OHG ) is an oxidatively damaged mutagenic base which causes G :C--&gt; T :A transversions in DNA .

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

<xsl:when test="@id='word_922'">

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

However , it was not clear whether OGG1 protein suppresses G :C--&gt; T :A transversions caused by 8-OHG in human cells in vivo .
In the present study we have examined the ability of OGG1 protein to suppress G :C--&gt; T :A transversions caused by 8-OHG in human cells by bacterial suppressor tRNA ( supF ) forward mutation assay using a shuttle vector DNA , pMY189 .
Introduction of a single 8-OHG residue at position 159 of the supF gene in plasmid pMY189 resulted in a 130 - fold increase in mutation frequency compared with untreated plasmid pMY189 after replication in the NCI-H1299 human lung cancer cell line .
G :C--&gt; T :A transversions at position 159 were detected in &gt; 90 % of the supF mutants from the 8-OHG - containing plasmid .
The mutation frequency of the 8-OHG - containing plasmid was significantly reduced by overexpression of OGG1 protein in NCI-H1299 cells and , in particular , the occurrence of G :C--&gt; T :A transversion at position 159 in the supF gene was suppressed .
Furthermore , frequencies and spectra of mutations of the untreated pMY189 plasmid did not differ significantly with overexpression of OGG1 protein .
These results indicate that OGG1 protein has the ability to suppress G :C--&gt; T :A transversions caused by 8-OHG in human cells in vivo .
******************************* END ABSTRACT 11532855 ***********************************************

</xsl:text>
<xsl:value-of select="mmax:endSuperscript()"/>
<xsl:value-of select="mmax:endItalic()"/>

</xsl:when>

<xsl:when test="@id='word_923'">
<xsl:value-of select="mmax:startItalic()"/>
<xsl:value-of select="mmax:startSuperscript()"/>
<xsl:text>
***************************** BEGIN ABSTRACT 10639135 ***********************************************
Regulation of expanded polyglutamine protein aggregation and nuclear localization by the glucocorticoid receptor .
Spinobulbar muscular atrophy and Huntington 's disease are caused by polyglutamine expansion in the androgen receptor and huntingtin , respectively , and their pathogenesis has been associated with abnormal nuclear localization and aggregation of truncated forms of these proteins .
Here we show , in diverse cell types , that glucocorticoids can up - or down - modulate aggregation and nuclear localization of expanded polyglutamine polypeptides derived from the androgen receptor and huntingtin through specific regulation of gene expression .
Wild - type glucocorticoid receptor ( GR ) , as well as C - terminal deletion derivatives , suppressed the aggregation and nuclear localization of these polypeptides , whereas mutations within the DNA binding domain and N terminus of GR abolished this activity .
Surprisingly , deletion of a transcriptional regulatory domain within the GR N terminus markedly increased aggregation and nuclear localization of the expanded polyglutamine proteins .

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

<xsl:when test="@id='word_951'">

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

Our findings suggest approaches to study the molecular pathogenesis and selective neuronal degeneration of polyglutamine expansion diseases .
******************************* END ABSTRACT 10639135 ***********************************************

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

<xsl:template match="bioentity:markable" mode="opening">
<xsl:value-of select="mmax:startBold()"/>
<xsl:value-of select="mmax:addLeftMarkableHandle(@mmax_level, @id, '[')"/>
<xsl:value-of select="mmax:endBold()"/>
</xsl:template>

<xsl:template match="bioentity:markable" mode="closing">
<xsl:value-of select="mmax:startBold()"/>
<xsl:value-of select="mmax:addRightMarkableHandle(@mmax_level, @id, ']')"/>
<xsl:value-of select="mmax:endBold()"/>
</xsl:template>
</xsl:stylesheet>
                
                
                   

