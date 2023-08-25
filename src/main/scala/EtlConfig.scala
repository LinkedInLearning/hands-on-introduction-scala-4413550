import pureconfig.*
import pureconfig.generic.derivation.default.*
import pureconfig.generic.derivation.EnumConfigReader

enum EtlImpl derives EnumConfigReader:
  case StringImpl, IntImpl, JsonImpl

case class EtlConfig(
    inputFilePath: String,
    outputFilePath: String,
    etlImpl: EtlImpl
) derives ConfigReader
