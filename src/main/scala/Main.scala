@main def run: Unit =
  val input: String = ???
  val output: String = ???
  etl(input, output)

def etl(inputFilePath: String, outputFilePath: String): Unit =
  val extracted = extract(inputFilePath)
  val transformed = transform(extracted)
  load(transformed, outputFilePath)
end etl

def extract(input: String): List[String] = ???
def transform(data: List[String]): List[String] = ???
def load(
    data: List[String],
    output: String = "src/main/resources/output.txt"
): Unit = ???
