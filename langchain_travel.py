from langchain_openai import ChatOpenAI
from langchain_core.prompts import PromptTemplate
from langchain_core.output_parsers import StrOutputParser
import os

# Set your OpenAI API key
os.environ["OPENAI_API_KEY"] = "your-api-key"

# Initialize the language model
llm = ChatOpenAI(
    model="gpt-4.1-mini",
    temperature=0.8
)

# Create a prompt template
travel_prompt = PromptTemplate(
    input_variables=["destination", "days"],
    template="""
You are an expert travel planner.

Create a {days}-day travel itinerary for {destination}.

Include:
- Top attractions
- Recommended local food
- Transportation tips
- Estimated daily budget

Return the itinerary in a clear, day-wise format.
"""
)

# Build the chain
chain = travel_prompt | llm | StrOutputParser()

# User input
destination = "Kyoto, Japan"
days = 4

# Generate itinerary
result = chain.invoke({
    "destination": destination,
    "days": days
})

print("\n===== Travel Plan =====\n")
print(result)
