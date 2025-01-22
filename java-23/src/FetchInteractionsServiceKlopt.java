
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;


public class FetchInteractionsServiceKlopt {
    private final IVRInteractionRepository ivrInteractionRepository;
    private final WebInteractionRepository webInteractionRepository;
    private final AppInteractionRepository appInteractionRepository;

    public FetchInteractionsServiceKlopt(IVRInteractionRepository ivrInteractionRepository,
                                         WebInteractionRepository webInteractionRepository,
                                         AppInteractionRepository appInteractionRepository) {
        this.ivrInteractionRepository = ivrInteractionRepository;
        this.webInteractionRepository = webInteractionRepository;
        this.appInteractionRepository = appInteractionRepository;
    }

    public List<Interaction> fetchInteractions(List<InteractionType> interactionTypes) {
        return interactionTypes.stream()
                .parallel()
                .flatMap(interactionType ->
                        faultSafe(() -> switch (interactionType) {
                            case InteractionType.IVR -> ivrInteractionRepository.fetchInteractions();
                            case InteractionType.WEB -> webInteractionRepository.fetchInteractions();
                            case InteractionType.APP -> appInteractionRepository.fetchInteractions();
                        }))
                .toList();
    }

    private Stream<Interaction> faultSafe(Supplier<Stream<Interaction>> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            return Stream.of();
        }
    }

    public static void main(String[] args) {
        FetchInteractionsServiceKlopt fetchInteractionsService = new FetchInteractionsServiceKlopt(
                new IVRInteractionRepository(),
                new WebInteractionRepository(),
                new AppInteractionRepository());
        fetchInteractionsService.fetchInteractions(List.of(InteractionType.IVR, InteractionType.WEB, InteractionType.APP));
    }
}

