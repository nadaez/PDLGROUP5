#Project 3 : MatrixSynthesizerWikipedia Groupe 5

#OpenCompare :
OpenCompare is an JAVA API that provides structured data from wikipedia.

#Data Source :
Json data has been imported from “opencompare.org”

#Objective of the project :
The purpose of this PDL project is to create matrices of comparisons (at CSV format)
from Wikipedia and / or Wikidata. These matrices are then made visualized and
exploited on the support that support the CSV import. For example, we would like to
compare "countries" (France, USA, Spain, China, etc.). We could then automatically
make a matrix with"Features" the number of inhabitants, the area, the GDP, the flag,
the language main practice, etc.

#Architecture :

User -> Group5.WikipediaAPI/Group5.WikidataAPI -> JSON -> CSV MAtrix -> OpenCompare -> Matrix Display

#Deployment Environment Requirements :
– Java => 1.8
– Eclipse / IntelliJ
– library used : Java Json
The user writes what he wants to search for in a config.txt file and launches
the main program. After starting the main program the matrix will be
generated automatically.