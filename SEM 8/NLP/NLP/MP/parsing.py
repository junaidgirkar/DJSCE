import os

from nltk.parse.corenlp import CoreNLPServer
from nltk.parse.corenlp import CoreNLPParser
from nltk.parse.corenlp import CoreNLPDependencyParser

java_path = "C:/Program Files/Java/jdk-18/bin/java.exe"
os.environ["JAVAHOME"] = java_path

STANFORD = os.path.join("models")

# Create the server
server = CoreNLPServer(
    os.path.join(STANFORD, "stanford-corenlp-4.4.0.jar"),
    os.path.join(STANFORD, "stanford-corenlp-4.4.0-models.jar"),
)

# Start the server
server.start()

# Constituency parsing
parser = CoreNLPParser()
parse = next(
    parser.raw_parse(
        "Work in psychology, anthropology, political science, and marketing suggests that humans are not gullible, but vigilant towards implausible information and tend to hold on to their prior beliefs."
    )
)
parse.draw()

# Dependency parsing
parser = CoreNLPDependencyParser()
parse = next(
    parser.raw_parse(
        "Work in psychology, anthropology, political science, and marketing suggests that humans are not gullible, but vigilant towards implausible information and tend to hold on to their prior beliefs."
    )
)
dot = parse.to_dot()
print(dot)

# Stop the server
server.stop()
