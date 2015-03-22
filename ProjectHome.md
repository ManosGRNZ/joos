Why another structural analysis program?

The idea for this program came from the experience in working for a research program (MEMSCON: http://www.memscon.com/) on the structural monitoring and assessment of buildings. While developing the theory for the structural assessment we faced the shortcommings of existing structural analysis programs that are (rightfully) considered state of the art. In particular, the SAP2000 and Sofistik, while being quite versatile and flexible working enviroments, they could not be adjusted in the ways that we needed for our project. Eventually we had to adapt our theory to the abilities that the SAP2000, which was our choice, provided. We also had to develop our own software modules and link them to SAP2000, which proved an inconvenient process since it is a closed source, proprietary platform.

This project aims to become a structural analysis program that will offer extreme flexibility. It does not aim to become a user friendly enviroment, although that would be possible by limiting the accessed features by a provided GUI. The ultimate goal is to take advantage of the Object Oriented design principles as they are implemented specificaly in the Java programming language in order to be able to model every situation in structural analysis simulation. The object orianted approach is considered suitable since every element and every concept in structural design can be easily modeled this way.

The innovation of the program will be that it is open to solve structural problems that cannot be addressed by the existing software. Smart materials and smart buildings, any geometry, structural responce that cannot be modeled with the existing theories... all problems could be addressed with this program.

The flexibility lies in the ability to define the properties of each "object". A material could follow any complex law. A section could follow its own laws. Even elements can be able to follow different laws.

The solution of these problems could be addressed by non-linear analysis. The formation of the siffness matrix could be constantly updated by each "current" condition (iteration) of the structure (e.g. geometrically non-linear analysis), of the elements (e.g. material non-linear analysis) or following any law. These abilities cannot be implemented in an easy to use GUI, since these require the provision for what the user might want to do. This approach takes for granted that the requirements of the user cannot be predicted.

Consiquently, this is a program that is intended for novel research. Every-day work and even extreme structures could be designed with the existing software that are not easy to compete with. Existing programmes include the design and building codes, which itself is a huge contribution that, at least initially, this project does not aim to fullfil.

Existing software in the structural analysis area have a history of decades. This means that most of them are implemented in older procedural languages like Fortran or C/C++. Most companies and universities work on their existing base, thus being constrained by the limitation of these older languages. Java is a modern programming language, using the object oriented approach. Therefore, the abilities to build on it offer better possibilities (although C++ offers comparable, if not more, features but at the cost of more difficult to maintain and extend code).

Last but not least, this is free software. Free, as defined by the Free Software Foundation (www.fsf.org), using the latest version of the General Public License. It is open for everyone to join. The git revision control system is used, thus making it easy to collaborate and even fork this project. Please feel free to work with us.


Manos Bairaktaris (bairaktaris@gmail.com)


A general framework of the program consists of the following:

1.  Developing in Java using Object Oriented practices everywhere
> i.		The coding process should always strive to be as simple as possible and well documented.
> ii. 	This is essential in order to make the project easier for new developers to join and easier for everyone to maintain.
> iii.	All choices should be made in order to make the coding easier for the developers. This reduces bugs and makes debugging easier.
> iv.		The above principles are inspired from the Arch linux project: "Code correctness is above user convenience"

2.  Separate ASCII files to define everything (model, loadcases, results) readable by the program and in tables
> in order to be easily imported and exported from and to spreadsheets.

3.  The objects will be of the following types/classes :
> i.		Nodes : including the supports, but not the springs (separate elements are easier to code)

> ii.		Materials :
> > subclasses will include linear, non-linear based on functions (question: will the type of material define the type of the analysis?)
> > Question: should there be separate materials for reinforcement, but each material should be defined with the properties of its reinforcement. (?)


> iii.	Sections:

> iv.		Elements :
> > All of them will be able to comply with any material.
> > Each element will instantiate its own material in order to apply a separate material law/function (will this increase the amount of memory? should linear materials be static in order to minimize the number of objects?)
> > Types of elements:
> > a. Truss
> > b. Beam
> > c. Plane triangular
> > d. Plane quad
> > e. cube


> v.		Groups of elements: these are used in order to organise the elements.

> vi.		Building : the sum of all elements

> vii.	Local Stiffness matrix : each element will instantiate its own matrix both on the local and the global coordinate systems

> viii.	Global Stiffness matrix : formed by the local matrices
> > This class will include the analysis types and trigger them(?)

4. 	User Interface:

> i.		The user should be able to configure any building using text files (imported/exported to spreadsheets) and a GUI.
> ii. 	The GUI will create the appropriate text files that the user can edit further.
> iii. 	The program will work with these text files exclusively.
> iv.		There could be the ability to develop a web interface for a cloud based service.

5. 	Results:
> i. 		There should be output in ASCII text form, easily imported to spreadsheet.
> ii.		A refined, presentable output could be formed with the use of LaTeX.
> iii.	The above ii. ability will provide the creation of PDF files that could be sent via e-mail from the web interface (see 4.iv)

6. Open Source: We need advice on releasing software

{ name: Java-based Object Oriented Structural (analysis and design programme) --> JOOS }