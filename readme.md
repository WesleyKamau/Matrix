<div align="left" style="position: relative;">
<img src="https://img.icons8.com/external-tal-revivo-regular-tal-revivo/96/external-readme-is-a-easy-to-build-a-developer-hub-that-adapts-to-the-user-logo-regular-tal-revivo.png" align="right" width="30%" style="margin: -20px 0 0 20px;">
<h1>MATRIX</h1>
<p align="left">
	<em><code>A Linear Algebra Practice Projects I created to practice common matrix algorithms.</code></em>
</p>
<p align="left">
	<img src="https://img.shields.io/github/license/WesleyKamau/Matrix?style=flat-square&logo=opensourceinitiative&logoColor=white&color=0080ff" alt="license">
	<img src="https://img.shields.io/github/last-commit/WesleyKamau/Matrix?style=flat-square&logo=git&logoColor=white&color=0080ff" alt="last-commit">
	<img src="https://img.shields.io/github/languages/top/WesleyKamau/Matrix?style=flat-square&color=0080ff" alt="repo-top-language">
	<img src="https://img.shields.io/github/languages/count/WesleyKamau/Matrix?style=flat-square&color=0080ff" alt="repo-language-count">
</p>
<p align="left">Built with the following language:</p>
<p align="left">
	<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=flat-square&logo=openjdk&logoColor=white" alt="java">
</p>
</div>
<br clear="right">

## 🔗 Table of Contents

- [📍 Overview](#-overview)
- [👾 Features](#-features)
- [📁 Project Structure](#-project-structure)
  - [📂 Project Index](#-project-index)
- [🚀 Getting Started](#-getting-started)
  - [☑️ Prerequisites](#-prerequisites)
  - [⚙️ Installation](#-installation)
  - [🤖 Usage](#🤖-usage)
  - [🧪 Testing](#🧪-testing)
- [📌 Project Roadmap](#-project-roadmap)
- [🔰 Contributing](#-contributing)
- [🎗 License](#-license)
- [🙌 Acknowledgments](#-acknowledgments)

---

## 📍 Overview

<code>This project provides the classes to implement Matrices, as well as perform several operations on them.
This can be used to perform computations, store data, and more!</code>

---

## 👾 Features

<code>There are two matrix classes, Matrix and Simplematrix.
The difference is that the parameter type of Matrix must extend the Linear class, whereas SimpleMatrix does not have this restrition.
This allows Matrix to support more mathematical operations whereas SimpleMatrix is simply a grid that stores data.\n
There are currently 4 Linear classes that may be used interchangably:
LinearInteger, an integer wrapper
LinearDouble, a double wrapper
LinearNaturalNumber, a wrapper of a custom Ohio State java component that represents the mathematical natural number set (see below on dependency)
and LinearVariable, a custom class meant to represent basic equations, still a wip
With these, the Matrix class supports a suite of operations, such as:
Addition
Subtraction
Augmenting
Transposing
Determinant
... and more!
Feel free to check it out!</code>

---

## 📁 Project Structure

```sh
└── Matrix/
    ├── CoolGridProject
    │   ├── .checkstyle
    │   ├── .classpath
    │   ├── .project
    │   ├── .settings
    │   │   └── org.eclipse.core.resources.prefs
    │   ├── bin
    │   │   └── .gitignore
    │   └── src
    │       └── CoolGridProject.java
    ├── Matrix
    │   ├── .checkstyle
    │   ├── .classpath
    │   ├── .project
    │   ├── .settings
    │   │   └── org.eclipse.core.resources.prefs
    │   ├── bin
    │   │   ├── .gitignore
    │   │   ├── CoolGridProject.class
    │   │   ├── Determinant.class
    │   │   ├── Matrix1LTest.class
    │   │   ├── Matrix2Test.class
    │   │   ├── MatrixHelper.class
    │   │   ├── MatrixIndex$Kind.class
    │   │   ├── MatrixIndex.class
    │   │   ├── MatrixTest.class
    │   │   ├── SystemOfEquationSolver.class
    │   │   ├── UserMatrix.class
    │   │   ├── VariableParser
    │   │   ├── VariableParserTest.class
    │   │   └── components
    │   ├── data
    │   │   └── saveTest
    │   ├── img
    │   │   ├── Checker_Black.png
    │   │   ├── Checker_Black_King.png
    │   │   ├── Checker_Red.png
    │   │   └── Checker_Red_King.png
    │   ├── src
    │   │   ├── Checkers
    │   │   ├── CoolGridProject.java
    │   │   ├── Determinant.java
    │   │   ├── MatrixHelper.java
    │   │   ├── MatrixIndex.java
    │   │   ├── SystemOfEquationSolver.java
    │   │   ├── UserMatrix.java
    │   │   ├── VariableParser
    │   │   └── components
    │   └── test
    │       ├── Matrix1LTest.java
    │       ├── Matrix2Test.java
    │       ├── MatrixTest.java
    │       ├── VariableParserTest.java
    │       └── components
    └── readme.md
```


### 📂 Project Index
<details open>
	<summary><b><code>MATRIX/</code></b></summary>
	<details> <!-- CoolGridProject Submodule -->
		<summary><b>CoolGridProject</b></summary>
		<blockquote>
			<details>
				<summary><b>src</b></summary>
				<blockquote>
					<table>
					<tr>
						<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/CoolGridProject/src/CoolGridProject.java'>CoolGridProject.java</a></b></td>
						<td><code>Simple project made in one day, utilizing SimpleMatrix to represent a coordinate plane, with shape plotting</code></td>
					</tr>
					</table>
				</blockquote>
			</details>
		</blockquote>
	</details>
	<details> <!-- Matrix Submodule -->
		<summary><b>Matrix</b></summary>
		<blockquote>
			<details>
				<summary><b>src</b></summary>
				<blockquote>
					<table>
					<tr>
						<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/Determinant.java'>Determinant.java</a></b></td>
						<td><code>Code to develop and test Determinant algoritm. Works but not thoroughly tested.</code></td>
					</tr>
					<tr>
						<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/SystemOfEquationSolver.java'>SystemOfEquationSolver.java</a></b></td>
						<td><code>Uses row reduction and Matrix to solve a system of equations.</code></td>
					</tr>
					<tr>
						<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/MatrixIndex.java'>MatrixIndex.java</a></b></td>
						<td><code>Helper class to store matrices created at runtime.</code></td>
					</tr>
					<tr>
						<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/UserMatrix.java'>UserMatrix.java</a></b></td>
						<td><code>Methods to help create matrices from user input.</code></td>
					</tr>
					<tr>
						<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/MatrixHelper.java'>MatrixHelper.java</a></b></td>
						<td><code>Matrix helper methods.</code></td>
					</tr>
					<tr>
						<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/CoolGridProject.java'>CoolGridProject.java</a></b></td>
						<td><code>Simple project made in one day, utilizing SimpleMatrix to represent a coordinate plane, with shape plotting</code></td>
					</tr>
					</table>
					<details>
						<summary><b>VariableParser</b></summary>
						<blockquote>
							<table>
							<tr>
								<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/VariableParser/VariableParser.java'>VariableParser.java</a></b></td>
								<td><code>Methods to generate a LinearVariable object from user String input.</code></td>
							</tr>
							</table>
						</blockquote>
					</details>
					<details>
						<summary><b>components</b></summary>
						<blockquote>
							<details>
								<summary><b>matrix</b></summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/components/matrix/Matrix2.java'>Matrix2.java</a></b></td>
										<td><code>Implementation of Matrix using the _UPDATE_ interface.</code></td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/components/matrix/MatrixSecondary.java'>MatrixSecondary.java</a></b></td>
										<td><code>Secondary methods for the Matrix interface.</code></td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/components/matrix/MatrixKernel.java'>MatrixKernel.java</a></b></td>
										<td><code>Kernel methods for the Matrix interface.</code></td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/components/matrix/Matrix1L.java'>Matrix1L.java</a></b></td>
										<td><code>Implementation of Matrix using the _UPDATE_ interface.</code></td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/components/matrix/SimpleMatrix.java'>SimpleMatrix.java</a></b></td>
										<td><code>Implementation of SimpleMatrix using the _UPDATE_.</code></td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/components/matrix/SimpleMatrix1L.java'>SimpleMatrix1L.java</a></b></td>
										<td><code>Implementation of SimpleMatrix using the _UPDATE_.</code></td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/components/matrix/Matrix.java'>Matrix.java</a></b></td>
										<td><code>Implementation of Matrix using the _UPDATE_.</code></td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/components/matrix/SimpleMatrixSecondary.java'>SimpleMatrixSecondary.java</a></b></td>
										<td><code>Secondary methods for SimpleMatrix.</code></td>
									</tr>
									</table>
								</blockquote>
							</details>
							<details>
								<summary><b>linear</b></summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/components/linear/LinearInteger.java'>LinearInteger.java</a></b></td>
										<td><code>Linear class implementation on int</code></td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/components/linear/Linear.java'>Linear.java</a></b></td>
										<td><code>Linear interface.</code></td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/components/linear/LinearVariable.java'>LinearVariable.java</a></b></td>
										<td><code>Linear class implementation on mathematical equations.</code></td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/components/linear/LinearDouble.java'>LinearDouble.java</a></b></td>
										<td><code>Linear class implementation on double</code></td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/components/linear/LinearNaturalNumber.java'>LinearNaturalNumber.java</a></b></td>
										<td><code>Linear class implementation on NaturalNumber</code></td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/components/linear/LinearSecondary.java'>LinearSecondary.java</a></b></td>
										<td><code>Secondary Linear Methods</code></td>
									</tr>
									</table>
								</blockquote>
							</details>
						</blockquote>
					</details>
					<details>
						<summary><b>Checkers</b></summary>
						<blockquote>
							<table>
							<tr>
								<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/Checkers/CheckersController.java'>CheckersController.java</a></b></td>
								<td><code>Controller for the Checkers game.</code></td>
							</tr>
							<tr>
								<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/Checkers/Checkers.java'>Checkers.java</a></b></td>
								<td><code>Checker board object code.</code></td>
							</tr>
							<tr>
								<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/Checkers/CheckersModel1.java'>CheckersModel1.java</a></b></td>
								<td><code>Model implementation for the Checkers game.</code></td>
							</tr>
							<tr>
								<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/Checkers/CheckersController1.java'>CheckersController1.java</a></b></td>
								<td><code>Controller implementation for the Checkers game.</code></td>
							</tr>
							<tr>
								<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/Checkers/Piece.java'>Piece.java</a></b></td>
								<td><code>Checker piece object code.</code></td>
							</tr>
							<tr>
								<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/Checkers/CheckersView1.java'>CheckersView1.java</a></b></td>
								<td><code>View implementation for the Checkers game.</code></td>
							</tr>
							<tr>
								<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/Checkers/CheckersView.java'>CheckersView.java</a></b></td>
								<td><code>View for the Checkers game.</code></td>
							</tr>
							<tr>
								<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/src/Checkers/CheckersModel.java'>CheckersModel.java</a></b></td>
								<td><code>Model for the Checkers game.</code></td>
							</tr>
							</table>
						</blockquote>
					</details>
				</blockquote>
			</details>
			<details>
				<summary><b>test</b></summary>
				<blockquote>
					<table>
					<tr>
						<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/test/Matrix2Test.java'>Matrix2Test.java</a></b></td>
						<td><code>Tests for Matrix2.</code></td>
					</tr>
					<tr>
						<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/test/VariableParserTest.java'>VariableParserTest.java</a></b></td>
						<td><code>Tests for VariableParser.</code></td>
					</tr>
					<tr>
						<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/test/MatrixTest.java'>MatrixTest.java</a></b></td>
						<td><code>Tests for Matrix.</code></td>
					</tr>
					<tr>
						<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/test/Matrix1LTest.java'>Matrix1LTest.java</a></b></td>
						<td><code>Tests for Matrix1L.</code></td>
					</tr>
					</table>
					<details>
						<summary><b>components</b></summary>
						<blockquote>
							<details>
								<summary><b>linear</b></summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/test/components/linear/LinearNaturalNumberTest.java'>LinearNaturalNumberTest.java</a></b></td>
										<td><code>Tests for LinearNaturalNumber.</code></td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/test/components/linear/LinearIntegerTest.java'>LinearIntegerTest.java</a></b></td>
										<td><code>Tests for LinearInteger.</code></td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/test/components/linear/LinearVariableTest.java'>LinearVariableTest.java</a></b></td>
										<td><code>Tests for LinerVariable.</code></td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/WesleyKamau/Matrix/blob/master/Matrix/test/components/linear/LinearDoubleTest.java'>LinearDoubleTest.java</a></b></td>
										<td><code>Tests for LinearDouble.</code></td>
									</tr>
									</table>
								</blockquote>
							</details>
						</blockquote>
					</details>
				</blockquote>
			</details>
		</blockquote>
	</details>
</details>

---
## 🚀 Getting Started

### ☑️ Prerequisites

Before getting started with Matrix, ensure your runtime environment meets the following requirements:

- **Programming Language:** Java

You will need the Ohio State Java components in order for parts of this project to work, such as LinearNaturalNumber, however 
they are not needed for any of the 1L implementations.

I will post instructions on how to install these in the future. In the meantime, enjoy playing with the other parts of this project!


### ⚙️ Installation

Install Matrix using one of the following methods:

I suggest importing this project into Eclipse, as it was used in the development of the project.



### 🤖 Usage
Run Matrix inside of Eclipse.

### 🧪 Testing
Testing is done through JUnit, which may be done by running the test cases inside of the JUnit tab in Eclipse.

---
## 📌 Project Roadmap

- [X] **`Task 1`**: <strike>Update Documentation.</strike>
- [ ] **`Task 2`**: Test LinearVariable.
- [ ] **`Task 3`**: Complete Determinant Algorithm.

---

## 🔰 Contributing

- **💬 [Join the Discussions](https://github.com/WesleyKamau/Matrix/discussions)**: Share your insights, provide feedback, or ask questions.
- **🐛 [Report Issues](https://github.com/WesleyKamau/Matrix/issues)**: Submit bugs found or log feature requests for the `Matrix` project.
- **💡 [Submit Pull Requests](https://github.com/WesleyKamau/Matrix/blob/main/CONTRIBUTING.md)**: Review open PRs, and submit your own PRs.

<details closed>
<summary>Contributing Guidelines</summary>

1. **Fork the Repository**: Start by forking the project repository to your github account.
2. **Clone Locally**: Clone the forked repository to your local machine using a git client.
   ```sh
   git clone https://github.com/WesleyKamau/Matrix
   ```
3. **Create a New Branch**: Always work on a new branch, giving it a descriptive name.
   ```sh
   git checkout -b new-feature-x
   ```
4. **Make Your Changes**: Develop and test your changes locally.
5. **Commit Your Changes**: Commit with a clear message describing your updates.
   ```sh
   git commit -m 'Implemented new feature x.'
   ```
6. **Push to github**: Push the changes to your forked repository.
   ```sh
   git push origin new-feature-x
   ```
7. **Submit a Pull Request**: Create a PR against the original project repository. Clearly describe the changes and their motivations.
8. **Review**: Once your PR is reviewed and approved, it will be merged into the main branch. Congratulations on your contribution!
</details>

<details closed>
<summary>Contributor Graph</summary>
<br>
<p align="left">
   <a href="https://github.com{/WesleyKamau/Matrix/}graphs/contributors">
      <img src="https://contrib.rocks/image?repo=WesleyKamau/Matrix">
   </a>
</p>
</details>

---

## 🎗 License

This project is protected under the [MIT](https://choosealicense.com/licenses/mit/) License. For more details, refer to the [LICENSE](https://choosealicense.com/licenses/mit/) file.

---

## 🙌 Acknowledgments

- Shout out to my Linear Algebra professor!

---